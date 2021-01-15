package net.thumbtack.school.notes.database.mappers;

import net.thumbtack.school.notes.database.model.Session;
import org.apache.ibatis.annotations.*;

@Mapper
public interface SessionMapper {

//    @Insert("INSERT INTO `notes`.`session` ( `userId`, `token`) "
//            + "VALUES ( #{userId}, #{token} );")
//    @Options(useGeneratedKeys = true, keyProperty = "session.id")
//    void insert(@Param("userId") int userId, @Param("token") String token);

    @Insert("INSERT INTO `notes`.`session` ( `userId`, `token`) "
            + "VALUES ( #{userId}, #{token} );")
    @Options(useGeneratedKeys = true, keyProperty = "session.id")
    int insert(Session session);

    @Update("UPDATE `notes`.`session` SET `token` = #{token} WHERE `session`.`id` = #{id}")
    void update(Session session);

    @Delete("DELETE FROM `notes`.`session` WHERE `session`.`id` = #{id};")
    void deleteById(@Param("id") int sectionId);

    @Delete("DELETE FROM `notes`.`session` WHERE `section`.`token` = #{token};")
    void deleteByToken(@Param("token") String token);

    @Delete("DELETE FROM `notes`.`session` WHERE `session`.`userId` = #{userId};")
    void deleteByUserId(int userId);

    @Select("SELECT * FROM `notes`.`session` WHERE `session`.`userId` = #{userID} ;")
    Session getByUserId(@Param("userId") int userId);

    @Select("SELECT * FROM `notes`.`session` WHERE `session`.`token` = #{token} ;")
    Session getByToken(@Param("token") String token);

    @Select("SELECT * FROM `notes`.`session` WHERE `session`.`id` = #{id} ;")
    Session getById(@Param("id") int id);

    @Select("SELECT `token` FROM `notes`.`session` WHERE `session`.`userId` = #{userId} ;")
    String getTokenByUserId(@Param("userId") int userId);

    @Select("SELECT `userId` FROM `notes`.`session` WHERE `session`.`token` = #{token} ;")
    int getUserIdByToken(@Param("token") String token);


}
