package net.thumbtack.school.notes.database;

import net.thumbtack.school.notes.UserStatus;
import net.thumbtack.school.notes.database.dao.DebugDao;
import net.thumbtack.school.notes.database.dao.SessionDao;
import net.thumbtack.school.notes.database.dao.SuperUserDao;
import net.thumbtack.school.notes.database.dao.UserDao;
import net.thumbtack.school.notes.database.daoimpl.DebugDaoImpl;
import net.thumbtack.school.notes.database.daoimpl.SessionDaoImpl;
import net.thumbtack.school.notes.database.daoimpl.SuperUserDaoImpl;
import net.thumbtack.school.notes.database.daoimpl.UserDaoImpl;
import net.thumbtack.school.notes.database.model.Session;
import net.thumbtack.school.notes.database.model.User;
import net.thumbtack.school.notes.serverexception.ServerException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Commit;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({SessionDaoImpl.class, UserDaoImpl.class, DebugDaoImpl.class, SuperUserDaoImpl.class})
@Commit
public class SessionDaoTest extends  DatabasePrepare{
    private final UserDao userDao;
    private final SessionDao sessionDao;

    @Autowired
    public SessionDaoTest(SessionDao sessionDao, UserDao userDao, DebugDao debugDao, SuperUserDao superUserDao) {
        super(userDao, debugDao, superUserDao);
        this.userDao = userDao;
        this.sessionDao = sessionDao;
    }

    @Test
    public void testInsert() throws ServerException {
        String token = UUID.randomUUID().toString();

        User user = new User(0,
                "User1",
                "User1",
                "User1",
                "Kolas",
                "User1",
                0.0,
                UserStatus.ACTIVE,
                LocalDateTime.now(),
                null);
        userDao.insert(user);
        User finalUser = userDao.getByLogin(user.getLogin(), user.getPassword() );

        Session session = new Session(finalUser.getId(), token);
        sessionDao.insert(session);
        Session retSess = sessionDao.getSessionByToken(token);
        assertAll(
                () -> assertEquals(finalUser.getId(), retSess.getUserId()),
                () -> assertEquals(token, retSess.getToken())
        );
    }

    @Test
    public void testUpdate() throws ServerException {
        String token = UUID.randomUUID().toString();

        User user = new User(0,
                "User1",
                "User1",
                "User1",
                "Kolas",
                "User1",
                0.0,
                UserStatus.ACTIVE,
                LocalDateTime.now(),
                null);
        userDao.insert(user);
        User finalUser = userDao.getByLogin(user.getLogin(), user.getPassword() );

        Session session = new Session(finalUser.getId(), UUID.randomUUID().toString());
        session = sessionDao.insert(session);
        session = sessionDao.getSessionByToken(session.getToken());
        session.setToken(token);
        sessionDao.update(session);
        Session retSess = sessionDao.getSessionByToken(session.getToken());
        assertAll(
                () -> assertEquals(finalUser.getId(), retSess.getUserId()),
                () -> assertEquals(token, retSess.getToken())
        );
    }

    @Test
    public void testGetTokenByUserId() throws ServerException {
        String token = UUID.randomUUID().toString();
        User user = new User(0,
                "User1",
                "User1",
                "User1",
                "Kolas",
                "User1",
                0.0,
                UserStatus.ACTIVE,
                LocalDateTime.now(),
                null);
        userDao.insert(user);
        User finalUser = userDao.getByLogin(user.getLogin(), user.getPassword() );
        Session session = new Session(finalUser.getId(), token);
        sessionDao.insert(session);
        String retToken = sessionDao.getTokenByUserId(finalUser.getId());
        assertAll(
                () -> assertEquals(token, retToken)
        );
    }

    @Test
    public void testGetUserIdByToken() throws ServerException {
        User user = new User(0,
                "User1",
                "User1",
                "User1",
                "Kolas",
                "User1",
                0.0,
                UserStatus.ACTIVE,
                LocalDateTime.now(),
                null);
        userDao.insert(user);
        User finalUser = userDao.getByLogin(user.getLogin(), user.getPassword() );
        Session session = new Session(finalUser.getId(), UUID.randomUUID().toString());
        sessionDao.insert(session);
        String token = sessionDao.getTokenByUserId(finalUser.getId());
        int retId = sessionDao.getUserIdByToken(token);
        Assertions.assertEquals(finalUser.getId(), retId);
    }

    @Test
    public void testGetSessionByToken() throws ServerException {
        User user = new User(0,
                "User1",
                "User1",
                "User1",
                "Kolas",
                "User1",
                0.0,
                UserStatus.ACTIVE,
                LocalDateTime.now(),
                null);
        userDao.insert(user);
        User finalUser = userDao.getByLogin(user.getLogin(), user.getPassword() );
        Session session = new Session(finalUser.getId(), UUID.randomUUID().toString());
        sessionDao.insert(session);
        Session retSess = sessionDao.getSessionByToken(session.getToken());
        assertAll(
                () -> assertEquals(finalUser.getId(), retSess.getUserId()),
                () -> assertEquals(session.getToken(), retSess.getToken())
        );
    }
}
