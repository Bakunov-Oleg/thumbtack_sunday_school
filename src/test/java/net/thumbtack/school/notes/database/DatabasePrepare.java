package net.thumbtack.school.notes.database;


import net.thumbtack.school.notes.UserStatus;
import net.thumbtack.school.notes.database.dao.*;
import net.thumbtack.school.notes.database.daoimpl.*;
import net.thumbtack.school.notes.database.model.User;
import net.thumbtack.school.notes.serverexception.ServerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;
import java.time.LocalDateTime;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({UserDaoImpl.class, DebugDaoImpl.class, SuperUserDaoImpl.class})
@Commit
public abstract class DatabasePrepare {

    private final UserDao userDao;
    private final DebugDao debugDao;
    private final SuperUserDao superUserDao;


    @Autowired
    public DatabasePrepare(UserDao userDao, DebugDao debugDao, SuperUserDao superUserDao) {
        this.userDao = userDao;
        this.debugDao = debugDao;
        this.superUserDao = superUserDao;
    }

    @BeforeEach
    public void clear() throws ServerException {
        debugDao.clear();
         User user = new User(0,
                "Admin",
                "Admin",
                "Admin",
                "Admin",
                "Admin",
                0.0,
                UserStatus.ACTIVE,
                LocalDateTime.now(),
                null);
        userDao.insert(user);
        User finalUser = userDao.getByLogin(user.getLogin(), user.getPassword());
        superUserDao.insert(finalUser.getId());
    }
}
