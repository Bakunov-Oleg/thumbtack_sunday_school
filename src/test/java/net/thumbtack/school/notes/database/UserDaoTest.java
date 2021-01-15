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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({SessionDaoImpl.class, UserDaoImpl.class, DebugDaoImpl.class, SuperUserDaoImpl.class})
@Commit
public class UserDaoTest extends DatabasePrepare {

    private final UserDao userDao;
    private final SessionDao sessionDao;

    @Autowired
    public UserDaoTest(SessionDao sessionDao, UserDao userDao, DebugDao debugDao, SuperUserDao superUserDao) {
        super(userDao, debugDao, superUserDao);
        this.userDao = userDao;
        this.sessionDao = sessionDao;
    }

    @Test
    public void testRegisterAndGetByLoginUser() throws ServerException {
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
        assertAll(
                () -> assertEquals(user.getFirstName(), finalUser.getFirstName()),
                () -> assertEquals(user.getLastName(), finalUser.getLastName()),
                () -> assertEquals(user.getPatronymic(), finalUser.getPatronymic()),
                () -> assertEquals(user.getLogin(), finalUser.getLogin()),
                () -> assertEquals(user.getPassword(), finalUser.getPassword()),
                () -> assertEquals(0.0, finalUser.getRating()),
                () -> assertEquals(UserStatus.ACTIVE, finalUser.getUserStatus())
        );
    }

    @Test
    public void testLoginAndLogout() throws ServerException {
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
        User finalUser = userDao.getByLogin(user.getLogin(), user.getPassword());
        userDao.login(finalUser);
        String token = sessionDao.getTokenByUserId(finalUser.getId());
        Assertions.assertNotNull(token);
        userDao.logout(token);
        token = sessionDao.getTokenByUserId(finalUser.getId());
        Assertions.assertNull(token);
    }

    @Test
    public void testUpdateUser() throws ServerException {
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
        User actualUser = userDao.getByLogin(user.getLogin(), user.getPassword());
        actualUser.setFirstName("newFirstName");
        actualUser.setLastName("newLastName");
        actualUser.setPatronymic("newPatronymic");
        actualUser.setPassword("newPassword");
        actualUser.setUserStatus(UserStatus.DELETED);
        actualUser.setCreateDate(LocalDateTime.now());
        userDao.update(actualUser);
        User finalUser = userDao.getByLogin(actualUser.getLogin(), actualUser.getPassword());
        assertAll(
                () -> assertEquals("newFirstName", finalUser.getFirstName()),
                () -> assertEquals("newLastName", finalUser.getLastName()),
                () -> assertEquals("newPatronymic", finalUser.getPatronymic()),
                () -> assertEquals("newPassword", finalUser.getPassword()),
                () -> assertEquals(0.0, finalUser.getRating()),
                () -> assertEquals(UserStatus.DELETED, finalUser.getUserStatus())
        );
    }

    @Test
    public void testExitOnServer() throws ServerException {
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
        User actualUser = userDao.getByLogin(user.getLogin(), user.getPassword());
        userDao.login(actualUser);
        String token = sessionDao.getTokenByUserId(actualUser.getId());
        userDao.exitOnServer(token);
        User finalUser = userDao.getByLogin(actualUser.getLogin(), actualUser.getPassword());
        Assertions.assertEquals(UserStatus.DELETED, finalUser.getUserStatus());
    }

    @Test
    public void testAddUserToIgnoreAndGetIgnored() throws ServerException {
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
        User actualUser = userDao.getByLogin(user.getLogin(), user.getPassword());
        user.setLogin("ignoredUser");
        userDao.insert(user);
        user.setLogin("ignoredUser2");
        userDao.insert(user);
        userDao.addUserToIgnore(actualUser.getId(), "ignoredUser");
        userDao.addUserToIgnore(actualUser.getId(), "ignoredUser2");
        List<User> ignoredUsers = userDao.getIgnoredByUserId(actualUser.getId());
        Set<String> ignoredLogins = new HashSet<>();
        for(User user1 : ignoredUsers){
            ignoredLogins.add(user1.getLogin());
        }
        assertAll(
                () -> assertTrue(ignoredLogins.contains("ignoredUser")),
                () -> assertTrue(ignoredLogins.contains("ignoredUser2"))
        );
    }

    @Test
    public void testAddUserToFollowingAndGetFollowing() throws ServerException {
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
        User actualUser = userDao.getByLogin(user.getLogin(), user.getPassword());
        user.setLogin("followeredUser");
        userDao.insert(user);
        user.setLogin("followeredUser2");
        userDao.insert(user);
        userDao.addUserToFollowing(actualUser.getId(), "followeredUser");
        userDao.addUserToFollowing(actualUser.getId(), "followeredUser2");
        List<User> followeredUsers = userDao.getFollowingByUserId(actualUser.getId());
        Set<String> followeredLogins = new HashSet<>();
        for(User user1 : followeredUsers){
            followeredLogins.add(user1.getLogin());
        }
        assertAll(
                () -> assertTrue(followeredLogins.contains("followeredUser")),
                () -> assertTrue(followeredLogins.contains("followeredUser2"))
        );
    }

    @Test
    public void delUserOnIgnore() throws ServerException {
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
        User actualUser = userDao.getByLogin(user.getLogin(), user.getPassword());
        user.setLogin("ignoredUser");
        userDao.insert(user);
        user.setLogin("ignoredUser2");
        userDao.insert(user);
        userDao.addUserToIgnore(actualUser.getId(), "ignoredUser");
        userDao.addUserToIgnore(actualUser.getId(), "ignoredUser2");
        userDao.delUserOnIgnore(actualUser.getId(), "ignoredUser");

        List<User> ignoredUsers = userDao.getIgnoredByUserId(actualUser.getId());
        Set<String> ignoredLogins = new HashSet<>();
        for(User user1 : ignoredUsers){
            ignoredLogins.add(user1.getLogin());
        }
        assertAll(
                () -> assertFalse(ignoredLogins.contains("ignoredUser")),
                () -> assertTrue(ignoredLogins.contains("ignoredUser2"))
        );
    }

    @Test
    public void delUserOnFollowing() throws ServerException {
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
        User actualUser = userDao.getByLogin(user.getLogin(), user.getPassword());
        user.setLogin("followeredUser");
        userDao.insert(user);
        user.setLogin("followeredUser2");
        userDao.insert(user);
        userDao.addUserToFollowing(actualUser.getId(), "followeredUser");
        userDao.addUserToFollowing(actualUser.getId(), "followeredUser2");
        userDao.delUserOnFollowing(actualUser.getId(), "followeredUser");
        List<User> followeredUsers = userDao.getFollowingByUserId(actualUser.getId());
        Set<String> followeredLogins = new HashSet<>();
        for(User user1 : followeredUsers){
            followeredLogins.add(user1.getLogin());
        }
        assertAll(
                () -> assertFalse(followeredLogins.contains("followeredUser")),
                () -> assertTrue(followeredLogins.contains("followeredUser2"))
        );
    }

    @Test
    public void testSetUserToSuperUser() throws ServerException {
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
        User actualUser = userDao.getByLogin(user.getLogin(), user.getPassword());
        userDao.setUserToSuperUser(actualUser.getId());
        Assertions.assertEquals(true, userDao.getUserIsSuperUserById(actualUser.getId()));
    }

    @Test
    public void testGetRatingById() throws ServerException {
        User user = new User(0,
                "User1",
                "User1",
                "User1",
                "Kolas",
                "User1",
                8.15,
                UserStatus.ACTIVE,
                LocalDateTime.now(),
                null);
        userDao.insert(user);
        User actualUser = userDao.getByLogin(user.getLogin(), user.getPassword());
        Assertions.assertEquals(user.getRating(), userDao.getRatingById(actualUser.getId()));
    }

    @Test
    public void testGetAccounts() throws ServerException {
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
        User actualUser = userDao.getByLogin(user.getLogin(), user.getPassword());
        user.setLogin("followeredUser");
        userDao.insert(user);
        user.setLogin("followeredUser2");
        userDao.insert(user);
        List<User> users = userDao.getAccounts(0, 0);
        System.out.println("sadas");
    }

    @Test
    public void testGetFollowingByUserId() throws ServerException {
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
        User actualUser = userDao.getByLogin(user.getLogin(), user.getPassword());
        user.setLogin("followeredUser");
        userDao.insert(user);
        user.setLogin("followeredUser2");
        userDao.insert(user);
        userDao.addUserToFollowing(actualUser.getId(), "followeredUser");
        userDao.addUserToFollowing(actualUser.getId(), "followeredUser2");
        List<User> followeredUsers = userDao.getFollowingByUserId(actualUser.getId());
        Set<String> followeredLogins = new HashSet<>();
        for(User user1 : followeredUsers){
            followeredLogins.add(user1.getLogin());
        }
        assertAll(
                () -> assertTrue(followeredLogins.contains("followeredUser")),
                () -> assertTrue(followeredLogins.contains("followeredUser2"))
        );
    }

    @Test
    public void testGetIgnoreByUserId() throws ServerException {
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
        User actualUser = userDao.getByLogin(user.getLogin(), user.getPassword());
        user.setLogin("ignoredUser");
        userDao.insert(user);
        user.setLogin("ignoredUser2");
        userDao.insert(user);
        userDao.addUserToIgnore(userDao.getByLogin("ignoredUser", user.getPassword()).getId(), "Kolas");
        userDao.addUserToIgnore(userDao.getByLogin("ignoredUser2", user.getPassword()).getId(), "Kolas");

        List<User> ignoredUsers = userDao.getIgnoreByUserId(actualUser.getId());
        Set<String> ignoredLogins = new HashSet<>();
        for(User user1 : ignoredUsers){
            ignoredLogins.add(user1.getLogin());
        }
        assertAll(
                () -> assertTrue(ignoredLogins.contains("ignoredUser")),
                () -> assertTrue(ignoredLogins.contains("ignoredUser2"))
        );
    }

    @Test
    public void testGetIgnoredByUserId() throws ServerException {
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
        User actualUser = userDao.getByLogin(user.getLogin(), user.getPassword());
        user.setLogin("ignoredUser");
        userDao.insert(user);
        user.setLogin("ignoredUser2");
        userDao.insert(user);
        userDao.addUserToIgnore(actualUser.getId(), "ignoredUser");
        userDao.addUserToIgnore(actualUser.getId(), "ignoredUser2");

        List<User> ignoredUsers = userDao.getIgnoredByUserId(actualUser.getId());
        Set<String> ignoredLogins = new HashSet<>();
        for(User user1 : ignoredUsers){
            ignoredLogins.add(user1.getLogin());
        }
        assertAll(
                () -> assertTrue(ignoredLogins.contains("ignoredUser")),
                () -> assertTrue(ignoredLogins.contains("ignoredUser2"))
        );
    }

    @Test
    public void testGetFollowersByUserId() throws ServerException {
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
        User actualUser = userDao.getByLogin(user.getLogin(), user.getPassword());
        user.setLogin("followeredUser");
        userDao.insert(user);
        user.setLogin("followeredUser2");
        userDao.insert(user);
        userDao.addUserToFollowing(userDao.getByLogin("followeredUser", user.getPassword()).getId(), "Kolas");
        userDao.addUserToFollowing(userDao.getByLogin("followeredUser2", user.getPassword()).getId(), "Kolas");
        List<User> followeredUsers = userDao.getFollowersByUserId(actualUser.getId());
        Set<String> followeredLogins = new HashSet<>();
        for(User user1 : followeredUsers){
            followeredLogins.add(user1.getLogin());
        }
        assertAll(
                () -> assertTrue(followeredLogins.contains("followeredUser")),
                () -> assertTrue(followeredLogins.contains("followeredUser2"))
        );
    }

    @Test
    public void testGetUsersExitOnServer() throws ServerException {
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
        User actualUser = userDao.getByLogin(user.getLogin(), user.getPassword());
        userDao.login(actualUser);
        String token = sessionDao.getTokenByUserId(actualUser.getId());
        userDao.exitOnServer(token);
        List<User> users = userDao.getUsersExitOnServer();
        Set<String> deletedUsers = new HashSet<>();
        for(User user1 : users){
            deletedUsers.add(user1.getLogin());
        }
        Assertions.assertTrue(deletedUsers.contains(userDao.getByLogin(user.getLogin(), user.getPassword()).getLogin()));
    }

    @Test
    public void testGetLoginIsOnline() throws ServerException {
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
        User actualUser = userDao.getByLogin(user.getLogin(), user.getPassword());
        userDao.login(actualUser);
        List<String> usersOnline = userDao.getLoginIsOnline();
        Assertions.assertTrue(usersOnline.contains("Kolas"));
    }
    @Test
    public void testGetLoginIsSuperUser() throws ServerException{
        List<String> superUsers = userDao.getLoginIsSuperUser();
        Assertions.assertTrue(superUsers.contains("Admin"));
    }

    @Test
    public void testGetAccountsHighRating() throws ServerException{
        User user = new User(0,
                "User1",
                "User1",
                "User1",
                "Kolas",
                "User1",
                9.0,
                UserStatus.ACTIVE,
                LocalDateTime.now(),
                null);
        userDao.insert(user);
        User actualUser = userDao.getByLogin(user.getLogin(), user.getPassword());
        List<User> users = userDao.getAccountsHighRating();
        Set<String> maxRateUsers = new HashSet<>();
        for(User user1 : users){
            maxRateUsers.add(user1.getLogin());
        }
        Assertions.assertTrue(maxRateUsers.contains("Kolas"));
        Assertions.assertFalse(maxRateUsers.contains("Admin"));
    }

    @Test
    public void testGetAccountsLowRating() throws ServerException{
        User user = new User(0,
                "User1",
                "User1",
                "User1",
                "Kolas",
                "User1",
                9.0,
                UserStatus.ACTIVE,
                LocalDateTime.now(),
                null);
        userDao.insert(user);
        User actualUser = userDao.getByLogin(user.getLogin(), user.getPassword());
        List<User> users = userDao.getAccountsLowRating();
        Set<String> maxRateUsers = new HashSet<>();
        for(User user1 : users){
            maxRateUsers.add(user1.getLogin());
        }
        Assertions.assertTrue(maxRateUsers.contains("Admin"));
        Assertions.assertFalse(maxRateUsers.contains("Kolas"));
    }
}