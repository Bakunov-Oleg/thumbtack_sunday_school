package net.thumbtack.school.notes.database.dao;

import net.thumbtack.school.notes.database.model.User;
import net.thumbtack.school.notes.serverexception.ServerException;

import java.util.List;

public interface UserDao {

    User insert(User user) throws ServerException;

    void update(User user) throws ServerException;

    User getByLogin(String login, String password) throws ServerException;

    User getByToken(String token) throws ServerException;

    User getById(int userId) throws ServerException;

    void login(User user) throws ServerException;

    void logout(String token) throws ServerException;

    void exitOnServer(String token) throws ServerException;

    void addUserToIgnore(int userId, String ignoredLogin) throws ServerException;

    void addUserToFollowing(int userId, String ignoredLogin) throws ServerException;

    void delUserOnIgnore(int userId, String ignoredLogin) throws ServerException;

    void delUserOnFollowing(int userId, String ignoredLogin) throws ServerException;

    void setUserToSuperUser(int userId) throws ServerException;

    Boolean getUserIsSuperUserById(int userId) throws ServerException;

    double getRatingById(int id) throws ServerException;

    List<User> getAccounts(int from, int count) throws ServerException;

    List<User> getFollowingByUserId(int userId) throws ServerException;

    List<User> getIgnoreByUserId(int userId) throws ServerException;

    List<User> getIgnoredByUserId(int userId) throws ServerException;

    List<User> getFollowersByUserId(int userId) throws ServerException;

    List<User> getUsersExitOnServer() throws ServerException;

    List<String> getLoginIsOnline() throws ServerException;

    List<String> getLoginIsSuperUser() throws ServerException;

    List<User> getAccountsHighRating() throws ServerException;

    List<User> getAccountsLowRating() throws ServerException;
}
