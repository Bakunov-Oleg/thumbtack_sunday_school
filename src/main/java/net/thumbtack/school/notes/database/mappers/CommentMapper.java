package net.thumbtack.school.notes.database.mappers;

import net.thumbtack.school.notes.database.model.Comment;
import net.thumbtack.school.notes.database.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Insert("INSERT INTO `notes`.`comment` ( `body`, `noteId`, `revisionId`, `authorId`, `created`) "
            + "VALUES ( #{subject}, #{sectionId}, #{authorId}, #{rating} );")
    @Options(useGeneratedKeys = true, keyProperty = "note.id")
    void insert(@Param("subject") String subject, @Param("sectionId") int sectionId, @Param("authorId") int authorId, @Param("rating") int rating);

    @Delete("DELETE FROM `notes`.`comment` WHERE `comment`.`id` = #{id};")
    void deleteById(@Param("id") int id);

    @Delete("DELETE FROM `notes`.`comment` WHERE `comment`.`authorId` = #{authorId};")
    void deleteByAuthorId(@Param("authorId") int authorId);

    @Select("SELECT * FROM `notes`.`comment` WHERE `comment`.`id` = #{id} ;")
    Comment getById(@Param("id") int id);

    @Select("SELECT * FROM `notes`.`comment` WHERE `comment`.`noteId` = #{noteId} ;")
    List<Comment> getByNoteId(@Param("noteId") int noteId);

    @Delete("DELETE FROM `notes`.`comment`;")
    void deleteAll();
}
