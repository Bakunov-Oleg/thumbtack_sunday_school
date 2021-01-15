package net.thumbtack.school.notes.database.daoimpl;

import net.thumbtack.school.notes.UserStatus;
import net.thumbtack.school.notes.database.dao.UserDao;
import net.thumbtack.school.notes.database.mappers.UserMapper;
import net.thumbtack.school.notes.database.model.User;
import net.thumbtack.school.notes.serverexception.ServerErrorCode;
import net.thumbtack.school.notes.serverexception.ServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Component
public class UserDaoImpl implements UserDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

    private UserMapper userMapper;

    public UserDaoImpl() {
    }

    @Autowired
    public UserDaoImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    @Transactional(rollbackFor = ServerException.class)
    public User insert(User user) throws ServerException {
        LOGGER.debug("DAO insert User, user login: {}", user.getLogin());

        try {
            int id = userMapper.insert(user);
            user.setId(id);
        } catch (DataAccessException ex) {
            LOGGER.info("Can't get insert by login: {}, {}", user.getLogin(), ex);
            throw new ServerException(ServerErrorCode.INVALID_LOGIN_OR_PASSWORD);
        }
        return user;
    }

    @Override
    @Transactional(rollbackFor = ServerException.class)
    public void update(User user) throws ServerException {
        LOGGER.debug("DAO update User, user login: {}", user.getLogin());
        try {
            userMapper.update(user);
        } catch (DataAccessException ex) {
            LOGGER.info("Can't get User by login: {}, {}", user.getLogin(), ex);
            throw new ServerException(ServerErrorCode.INVALID_LOGIN_OR_PASSWORD);
        }
    }

    @Override
    public User getByLogin(String login, String password) throws ServerException {
        LOGGER.debug("DAO get User by login: {}", login);
        try {
            return userMapper.getByLogin(login, password);
        } catch (DataAccessException ex) {
            LOGGER.info("Can't get User by login: {}, {}", login, ex);
            throw new ServerException(ServerErrorCode.INVALID_LOGIN_OR_PASSWORD);
        }
    }

    @Override
    public User getByToken(String token) throws ServerException {
        LOGGER.debug("DAO get User by token: {}", token);
        try {
            return userMapper.getByToken(token);
        } catch (DataAccessException ex) {
            LOGGER.info("Can't get User by token: {}, {}", token, ex);
            throw new ServerException(ServerErrorCode.INVALID_TOKEN);
        }
    }

    @Override
    public User getById(int userId) throws ServerException {
        LOGGER.debug("DAO get User by userId: {}", userId);
        try {
            return userMapper.getById(userId);
        } catch (DataAccessException ex) {
            LOGGER.info("Can't get User by userId: {}, {}", userId, ex);
            throw new ServerException(ServerErrorCode.INVALID_TOKEN);
        }
    }

    @Override
    public void login(User user) throws ServerException {
        LOGGER.debug("DAO Login user: {}", user.getLogin());
        try {
            String token = UUID.randomUUID().toString();
            userMapper.login(user.getId(), token);
        } catch (DataAccessException ex) {
            LOGGER.info("Can't Login user {} {} ", user.getLogin(), ex);
            throw new ServerException(ServerErrorCode.INVALID_LOGIN_OR_PASSWORD);
        }
    }

    @Override
    public void logout(String token) throws ServerException {
        LOGGER.debug("DAO Logout user with token: {} ", token);
        try {
            userMapper.logout(token);
        } catch (DataAccessException ex) {
            LOGGER.info("Can't Logout user with token: {} {} ", token, ex);
            throw new ServerException(ServerErrorCode.INVALID_TOKEN);
        }
    }

    @Override
    public void exitOnServer(String token) throws ServerException {
        LOGGER.debug("DAO ExitOnServer user with token: {} ", token);
        try {
            User user = userMapper.getByToken(token);
            user.setUserStatus(UserStatus.DELETED);
            userMapper.update(user);
        } catch (DataAccessException ex) {
            LOGGER.info("Can't ExitOnServer user with token: {} {} ", token, ex);
            throw new ServerException(ServerErrorCode.INVALID_TOKEN);
        }
    }

    @Override
    public void addUserToIgnore(int userId, String ignoredLogin) throws ServerException {
        LOGGER.debug("Add User By login: {} to ignored list for userID: {} ", ignoredLogin, userId);
        try {
            userMapper.addUserToIgnore(userId, ignoredLogin);
        } catch (DataAccessException ex) {
            LOGGER.info("Can't addUserToIgnore: {} ", ex);
            throw new ServerException(ServerErrorCode.ENTRY_IS_ALREDY_IN_DATABASE);
        }
    }

    @Override
    public void addUserToFollowing(int userId, String ignoredLogin) throws ServerException {
        LOGGER.debug("Add User By login: {} to following list for userID: {} ", ignoredLogin, userId);
        try {
            userMapper.addUserToFollowing(userId, ignoredLogin);
        } catch (DataAccessException ex) {
            LOGGER.info("Can't addUserToFollowing: {} ", ex);
            throw new ServerException(ServerErrorCode.ENTRY_IS_ALREDY_IN_DATABASE);
        }
    }

    @Override
    public void delUserOnIgnore(int userId, String ignoredLogin) throws ServerException {
        LOGGER.debug("Dell User By login: {} on ignore list for userID: {} ", ignoredLogin, userId);
        try {
            userMapper.delUserOnIgnore(userId, ignoredLogin);
        } catch (DataAccessException ex) {
            LOGGER.info("Can't addUserToIgnore: {} ", ex);
            throw new ServerException(ServerErrorCode.ENTRY_IS_NOT_FIND);
        }
    }

    @Override
    public void delUserOnFollowing(int userId, String ignoredLogin) throws ServerException {
        LOGGER.debug("Dell User By login: {} on following list for userID: {} ", ignoredLogin, userId);
        try {
            userMapper.delUserOnFollowing(userId, ignoredLogin);
        } catch (DataAccessException ex) {
            LOGGER.info("Can't delUserOnFollowing: {} ", ex);
            throw new ServerException(ServerErrorCode.ENTRY_IS_NOT_FIND);
        }
    }

    @Override
    public void setUserToSuperUser(int userId) throws ServerException {
        LOGGER.debug("Set User to SuperUSer By userId: {} on SuperUser By token: {} ", userId);
        try {
            userMapper.setUserToSuperUserByUserId(userId);
        } catch (DataAccessException ex) {
            LOGGER.info("Can't set userId: {} to superUser by ex: {}", userId, ex);
            throw new ServerException(ServerErrorCode.ENTRY_IS_NOT_FIND);
        }
    }

    @Override
    public Boolean getUserIsSuperUserById(int userId) throws ServerException {
        LOGGER.debug("Get User is SuperUSer By userId: {} on SuperUser By token: {} ", userId);
        try {
            if( userMapper.getUserIsSuperUserById(userId) != 0){
                return true;
            } else {
                return false;
            }
        } catch (DataAccessException ex) {
            LOGGER.info("Can't get User is SuperUSer By userId: {} by ex: {}", userId, ex);
            throw new ServerException(ServerErrorCode.ENTRY_IS_NOT_FIND);
        }
    }

    @Override
    public double getRatingById(int id) throws ServerException {
        LOGGER.debug("Ger user rating by userId: {} ", id);
        try {
            return userMapper.getRatingById(id);
        } catch (DataAccessException ex) {
            LOGGER.info("Can't delUserOnFollowing: {} ", ex);
            throw new ServerException(ServerErrorCode.ENTRY_IS_NOT_FIND);
        }
    }

    @Override
    public List<User> getAccounts(int from, int count) throws ServerException {
        LOGGER.debug("get Accounts");
        try {
            if (count == 0) {
                return userMapper.getAccounts(from, Integer.MAX_VALUE);
            } else {
                return userMapper.getAccounts(from, count);
            }
        } catch (DataAccessException ex) {
            LOGGER.info("Can't get all users by ex: {} ", ex);
            throw new ServerException(ServerErrorCode.ENTRY_IS_NOT_FIND);
        }
    }

    @Override
    public List<User> getFollowingByUserId(int userId) throws ServerException {
        LOGGER.debug("Get Following By UserId");
        try {
            return userMapper.getFollowingByUserId(userId);
        } catch (DataAccessException ex) {
            LOGGER.info("Following By User Id {} ", ex);
            throw new ServerException(ServerErrorCode.ENTRY_IS_NOT_FIND);
        }
    }

    @Override
    public List<User> getIgnoredByUserId(int userId) throws ServerException {
        LOGGER.debug("Get Following By UserId");
        try {
            return userMapper.getIgnoredByUserId(userId);
        } catch (DataAccessException ex) {
            LOGGER.info("Following By User Id {} ", ex);
            throw new ServerException(ServerErrorCode.ENTRY_IS_NOT_FIND);
        }
    }

    @Override
    public List<User> getIgnoreByUserId(int userId) throws ServerException {
        LOGGER.debug("Get Following By UserId");
        try {
            return userMapper.getIgnoreByUserId(userId);
        } catch (DataAccessException ex) {
            LOGGER.info("Following By User Id {} ", ex);
            throw new ServerException(ServerErrorCode.ENTRY_IS_NOT_FIND);
        }
    }

    @Override
    public List<User> getFollowersByUserId(int userId) throws ServerException {
        LOGGER.debug("Get Following By UserId");
        try {
            return userMapper.getFollowersByUserId(userId);
        } catch (DataAccessException ex) {
            LOGGER.info("Following By User Id {} ", ex);
            throw new ServerException(ServerErrorCode.ENTRY_IS_NOT_FIND);
        }
    }

    @Override
    public List<User> getUsersExitOnServer() throws ServerException {
        LOGGER.debug("Get users existing on server");
        try {
            return userMapper.getUsersExitOnServer();
        } catch (DataAccessException ex) {
            LOGGER.info("Get users existing on server {} ", ex);
            throw new ServerException(ServerErrorCode.ENTRY_IS_NOT_FIND);
        }
    }

    @Override
    public List<String> getLoginIsOnline() throws ServerException {
        LOGGER.debug("Get users is online");
        try {
            return userMapper.getOnlineUserLogins();
        } catch (DataAccessException ex) {
            LOGGER.info("Don't get online users {} ", ex);
            throw new ServerException(ServerErrorCode.ENTRY_IS_NOT_FIND);
        }
    }

    @Override
    public List<String> getLoginIsSuperUser() throws ServerException {
        LOGGER.debug("Get users is online");
        try {
            return userMapper.getSuperUserLogins();
        } catch (DataAccessException ex) {
            LOGGER.info("Don't get superuser users {} ", ex);
            throw new ServerException(ServerErrorCode.ENTRY_IS_NOT_FIND);
        }
    }


    @Override
    public List<User> getAccountsHighRating() throws ServerException {
        LOGGER.debug("Get users HighRating");
        try {
            return userMapper.getAccountsHighRating();
        } catch (DataAccessException ex) {
            LOGGER.info("Don't get superuser users {} ", ex);
            throw new ServerException(ServerErrorCode.ENTRY_IS_NOT_FIND);
        }
    }

    @Override
    public List<User> getAccountsLowRating() throws ServerException {
        LOGGER.debug("Get users LowRating");
        try {
            return userMapper.getAccountsLowRating();
        } catch (DataAccessException ex) {
            LOGGER.info("Don't get superuser users {} ", ex);
            throw new ServerException(ServerErrorCode.ENTRY_IS_NOT_FIND);
        }
    }
}
