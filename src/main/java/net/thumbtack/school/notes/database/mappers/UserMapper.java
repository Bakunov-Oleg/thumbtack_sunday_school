package net.thumbtack.school.notes.database.mappers;

import net.thumbtack.school.notes.database.model.Session;
import net.thumbtack.school.notes.database.model.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;


@Mapper
public interface UserMapper {

    @Insert("INSERT INTO `user` ( `firstName`, `lastName`, `patronymic`, `login`, `password`, `userStatus`, `rating`, `createDate`) "
            + "VALUES ( #{firstName}, #{lastName}, #{patronymic}, #{login}, #{password}, #{userStatus}, #{rating}, NOW() );")
    @Options(useGeneratedKeys = true, keyProperty = "user.id", keyColumn = "user.id")
    int insert(User user);

    @Update("UPDATE `user` SET `firstName` = #{firstName}, `lastName` = #{lastName}, `patronymic` = #{patronymic}, `password` = #{password}, `userStatus` = #{userStatus} WHERE id = #{id}")
    void update(User user);

    @Delete("DELETE FROM `notes`.`user` WHERE `user`.`id` = #{id};")
    void deleteById(int id);

    @Delete("DELETE FROM `notes`.`user`;")
    void deleteAll();

// REVU имя БД указывать не надо
// просто  "SELECT * FROM user
    @Select("SELECT * FROM `notes`.`user` WHERE `user`.`login` = #{login} AND `user`.`password` = #{password};")
    User getByLogin(@Param("login") String login, @Param("password") String password);

    @Select("SELECT * FROM `notes`.`user` "
            + "JOIN `session` ON `session`.`userId` = `user`.`id` "
            + "WHERE session.token = #{token};")
    User getByToken(String token);

    @Select("SELECT * FROM `notes`.`user` WHERE `user`.`id` = #{userId} ;")
    User getById(@Param("userId") int userId);

    @Update("REPLACE INTO `notes`.`session` ( `userId`, `token`) "
            + "VALUES ( #{userId}, #{token} );")
    @Options(useGeneratedKeys = true, keyProperty = "session.id")
    void login(@Param("userId") int userId, @Param("token") String token);

    @Delete("DELETE FROM `notes`.`session` WHERE `token` = #{token};")
    void logout(@Param("token") String token);

    @Insert("INSERT INTO `notes`.`ignore` ( `idUser1`, `idUser2`) "
            + "VALUES( #{userId}, (SELECT `user`.`id` FROM `notes`.`user` WHERE `login` = #{login}))")
    void addUserToIgnore(@Param("userId") int id, @Param("login") String ignoredLogin);

    @Select("SELECT COUNT(*) FROM `notes`.`ignore` WHERE `ignore`.`idUser1` = #{idUser1} AND `ignore`.`idUser2` = #{idUser2} ;")
    int isIgnore(@Param("idUser1") int idUser1, @Param("idUser2") int idUser2);

    @Insert("INSERT INTO `notes`.`follower` ( `idUser1`, `idUser2`) "
            + "VALUES( #{userId}, (SELECT `user`.`id` FROM `notes`.`user` WHERE `login` = #{login}))")
    void addUserToFollowing(@Param("userId") int id, @Param("login") String ignoredLogin);

    @Delete("DELETE FROM `notes`.`ignore` WHERE `idUser1` = #{userId} AND `idUser2` = (SELECT `user`.`id` FROM `notes`.`user` WHERE `login` = #{login})")
    void delUserOnIgnore(@Param("userId") int id, @Param("login") String ignoredLogin);

    @Delete("DELETE FROM `notes`.`follower` WHERE `idUser1` = #{userId} AND `idUser2` = (SELECT `user`.`id` FROM `notes`.`user` WHERE `login` = #{login})")
    void delUserOnFollowing(@Param("userId") int id, @Param("login") String ignoredLogin);

    @Insert("INSERT INTO `notes`.`superuser` (`userId`) VALUES ( #{userId});")
    void setUserToSuperUserByUserId(@Param("userId") int userId);

    @Select("SELECT COUNT `notes`.`superuser` "
            + "WHERE superuser.id = "
            + "SELECT * FROM `notes`.`user` "
            + "JOIN `session` ON `session`.`userId` = `user`.`id`"
            + "WHERE session.token = #{token}")
    int getUserIsSuperUserByToken(@Param("token") String token);

    @Select("SELECT COUNT(*) FROM `notes`.`superuser` WHERE `superuser`.`userId` = #{userId};")
    int getUserIsSuperUserById(@Param("userId") int userId);

//    @Select("SELECT COUNT `notes`.`superuser` "
//            + "WHERE superuser.id = "
//            + "SELECT * FROM `notes`.`user` "
//            + "JOIN `session` ON `session`.`userId` = `user`.`id`"
//            + "WHERE session.token = #{token}")
//    int getUserIsSuperUserByLogin(@Param("token") String token);

    @Select("SELECT rating FROM `notes`.`user` "
            + "WHERE user.id = #{id}")
    double getRatingById(@Param("id") int id);

    @Select("SELECT * FROM `notes`.`user` LIMIT #{from}, #{count}")
    List<User> getAccounts(@Param("from") int from, @Param("count") int count);

    @Select("SELECT * FROM `notes`.`user` WHERE user.id in "
            + "(SELECT `idUser2` FROM `notes`.`follower` WHERE `follower`.`idUser1` = #{userId})")
    List<User> getFollowingByUserId(@Param("userId") int userId);

    @Select("SELECT * FROM `notes`.`user` WHERE `user`.`id` in "
            + "(SELECT `idUser2` FROM `notes`.`ignore` WHERE `ignore`.`idUser1` = #{userId})")
    List<User> getIgnoredByUserId(@Param("userId") int userId);

    @Select("SELECT * FROM `notes`.`user` WHERE `user`.`id` in "
            + "(SELECT `idUser1` FROM `notes`.`ignore` WHERE `ignore`.`idUser2` = #{userId})")
    List<User> getIgnoreByUserId(@Param("userId") int userId);

    @Select("SELECT * FROM `notes`.`user` WHERE user.id in "
            + "(SELECT `idUser1` FROM `notes`.`follower` WHERE `follower`.`idUser2` = #{userId})")
    List<User> getFollowersByUserId(@Param("userId") int userId);

    @Select("SELECT * FROM `notes`.`user` WHERE `user`.`userStatus` = 'DELETED'")
    List<User> getUsersExitOnServer();

    @Select("SELECT `login` FROM `notes`.`user` WHERE user.id in "
            + "(SELECT `userId` FROM `notes`.`session`)")
    List<String> getOnlineUserLogins();

    @Select("SELECT `login` FROM `notes`.`user` WHERE user.id in "
            + "(SELECT `userId` FROM `notes`.`superuser`)")
    List<String> getSuperUserLogins();

    @Select("SELECT * FROM `notes`.`user` WHERE `user`.`rating` = (SELECT max(`rating`) FROM `notes`.`user`)")
    List<User> getAccountsHighRating();

    @Select("SELECT * FROM `notes`.`user` WHERE `user`.`rating` = (SELECT min(`rating`) FROM `notes`.`user`)")
    List<User> getAccountsLowRating();

}
