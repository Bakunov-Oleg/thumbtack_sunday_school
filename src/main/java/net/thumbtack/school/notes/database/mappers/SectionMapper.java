package net.thumbtack.school.notes.database.mappers;

import net.thumbtack.school.notes.database.model.Section;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface SectionMapper {

    @Insert("INSERT INTO `notes`.`section` (`id`, `name`, `authorId`) VALUES ( null, #{name}, #{authorId} );")
    @Options(useGeneratedKeys = true, keyProperty = "section.id", keyColumn="id")
    int insert(@Param("name") String name, @Param("authorId") int authorId);

    @Select("SELECT @@identity FROM `notes`.`section`")
    int getLastInsertId();

    @Update({"<script>",
            "UPDATE `notes`.`section` ",
            "<set>",
            "<if test='name != null'> `name` = #{name}, </if>",
            "</set>",
            "<where> id = #{id}</where>",
            "</script>"})
    Section update(Section section);

    @Delete("DELETE FROM `notes`.`section` WHERE `section`.`id` = #{sectionId};"
            + "DELETE FROM `notes`.`note` WHERE `note`.`sectionId` = #{sectionId};")
    void deleteById(@Param("id") int sectionId);

    @Select("SELECT * FROM `notes`.`section` WHERE `section`.`id` = #{id} ;")
    Section getById(@Param("id") int id);

    @Select("SELECT * FROM `notes`.`section`")
    @Results({
            @Result(property = "id", column = "id"),
    })
    List<Section> getAll();

    @Delete("DELETE FROM `notes`.`section`;")
    void deleteAll();
}
