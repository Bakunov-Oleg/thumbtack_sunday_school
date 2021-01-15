package net.thumbtack.school.notes.database.mappers;

import org.apache.ibatis.annotations.*;

@Mapper
public interface SuperUserMapper {

    @Insert("INSERT INTO `notes`.`superuser` (`userId`) VALUES ( #{userId});")
    @Options(useGeneratedKeys = true, keyProperty = "superuser.id")
    void insert(@Param("userId") int userId);

    @Delete("DELETE FROM `notes`.`superuser`;")
    void deleteAll();
}
