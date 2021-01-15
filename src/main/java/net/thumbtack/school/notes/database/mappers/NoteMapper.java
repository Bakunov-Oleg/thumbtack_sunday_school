package net.thumbtack.school.notes.database.mappers;

import net.thumbtack.school.notes.database.model.Comment;
import net.thumbtack.school.notes.database.model.Note;
import net.thumbtack.school.notes.database.model.Section;
import net.thumbtack.school.notes.database.model.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface NoteMapper {

    @Select("SELECT @@identity FROM `notes`.`note`")
    int getLastInsertId();

    @Insert("INSERT INTO `notes`.`note` ( `subject`, `sectionId`, `authorId`, `rating`) "
            + "VALUES ( #{subject}, #{sectionId}, #{authorId}, #{rating} );")
    @Options(useGeneratedKeys = true, keyProperty = "note.id", keyColumn = "note.id")
    int insert(@Param("authorId") int authorId, @Param("subject") String subject, @Param("sectionId") int sectionId, @Param("rating") double rating);

    @Update("UPDATE `notes`.`note` SET sectionId = #{sectionId} WHERE id = #{noteId};")
    void reposition(@Param("noteId") int noteId, @Param("sectionId") int sectionId);





    @Select("SELECT * FROM `notes`.`note` WHERE `note`.`id` = #{noteId};")
    @Results({
            @Result(property = "id", column = "id"),
//            @Result(property = "body", column = "id", javaType = String.class,
//                    one = @One(select = "net.thumbtack.school.notes.database.mappers.NoteRevisionMapper.getBodyByNoteId",
//                            fetchType = FetchType.EAGER)),
            @Result(property = "author", column = "authorId", javaType = User.class,
                    one = @One(select = "net.thumbtack.school.notes.database.mappers.UserMapper.getById",
                            fetchType = FetchType.EAGER)),
            @Result(property = "section", column = "sectionId", javaType = Section.class,
                    one = @One(select = "net.thumbtack.school.notes.database.mappers.SectionMapper.getById",
                            fetchType = FetchType.EAGER)),
            @Result(property = "comments", column = "id", javaType = Comment.class,
                    many = @Many(select = "net.thumbtack.school.notes.database.mappers.CommentMapper.getByNoteId",
                            fetchType = FetchType.LAZY)),
            @Result(property = "revisions", column = "id", javaType = Note.class,
                    many = @Many(select = "net.thumbtack.school.notes.database.mappers.NoteRevisionMapper.getAllRevisionsByNote",
                            fetchType = FetchType.LAZY))
    })
    Note getNoteById(@Param("noteId") int noteId);


    @Select({"<script>",
            "SELECT * FROM `notes`.`note`  WHERE",
            "<set>",
            "<if test='sectionId != null'> `sectionId` = #{sectionId}, </if>",

            "<if test='sortByRating == asc'> ORDER BY `rating` ASC, </if>",
            "<if test='sortByRating == desc'> ORDER BY `rating` DESC, </if>",



            "<if test='patronymic != null'> `patronymic` = #{patronymic}, </if>",
            "<if test='password != null'> `password` = MD5(#{password}) </if>",
            "</set>",
            "<where> id = #{id}</where>",
            "</script>"})

    List<Note> getAll(@Param("sectionId") int sectionId,
                      @Param("sortByRating") String sortByRating,
                      @Param("tags") String tags,
                      @Param("alltags") Boolean alltags,
                      @Param("timeFrom") LocalDateTime timeFrom,
                      @Param("timeTo") LocalDateTime timeTo,
                      @Param("userId") int user,
                      @Param("include") String include,
                      @Param("comments") Boolean comments,
                      @Param("allVersions") Boolean allVersions,
                      @Param("commentVersion") Boolean commentVersion,
                      @Param("from") int from,
                      @Param("count") int count);










    @Delete("DELETE FROM `notes`.`note` WHERE `note`.`id` = #{id};")
    void deleteById(@Param("id") int id);

    @Select("DELETE * FROM `notes`.`note` WHERE `note`.`authorId` = #{authorId} ;")
    void deleteByUser(@Param("authorId") int authorId);

    @Select("SELECT * FROM `notes`.`note` WHERE `note`.`id` = #{id} ;")
    Note getById(@Param("id") int id);

//    @Select("SELECT * FROM `notes`.`section` ;")
//    List<Note> getAll();

    @Update("UPDATE `notes`.`note` SET rating = #{rating} WHERE id = #{noteId};")
    void evaluateRating(@Param("noteId") int noteId, @Param("rating") double rating);

    @Select("SELECT `rating` FROM `notes`.`note` WHERE note.id = #{noteId};")
    double getRatingByNoteId(@Param("noteId") int noteId);

    @Delete("DELETE FROM `notes`.`note`;")
    void deleteAll();
}
