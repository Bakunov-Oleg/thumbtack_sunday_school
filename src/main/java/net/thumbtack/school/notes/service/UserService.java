package net.thumbtack.school.notes.service;

import net.thumbtack.school.notes.ApplicationProperties;
import net.thumbtack.school.notes.database.dao.UserDao;
import net.thumbtack.school.notes.database.model.Session;
import net.thumbtack.school.notes.database.model.User;
import net.thumbtack.school.notes.dto.request.*;
import net.thumbtack.school.notes.dto.response.EditAccountDtoResponse;
import net.thumbtack.school.notes.dto.response.EmptyDtoResponse;
import net.thumbtack.school.notes.dto.response.GetAccountsDtoResponse;
import net.thumbtack.school.notes.dto.response.RegisterUserDtoResponse;
import net.thumbtack.school.notes.mappers.UserMapper;
import net.thumbtack.school.notes.serverexception.ServerErrorCode;
import net.thumbtack.school.notes.serverexception.ServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional(rollbackFor = DataAccessException.class)
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final UserDao userDao;

    @Autowired
    ApplicationProperties properties;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }


    /**
     * 7.2. Регистрация пользователя.
     * POST /api/accounts
     *
     * @param dtoRequest
     * @return RegisterUserDtoResponse, cookie	JAVASESSIONID
     * @throws ServerException
     */
    public RegisterUserDtoResponse registerUser(String cookie, RegisterUserDtoRequest dtoRequest) throws ServerException {
        LOGGER.debug("Action \"Register\" for user: {}", dtoRequest.getLogin());
        User user = UserMapper.convertRegisterUserDtoRequestToUser(dtoRequest);
        user = userDao.insert(user);
        userDao.login(user);
        return UserMapper.convertUserToRegisterUserDtoResponse(user);
    }

    /**
     * 7.3. Login
     * POST /api/sessions
     *
     * @param dtoRequest
     * @return cookie    JAVASESSIONID
     * @throws ServerException
     */
    public String loginUser(LoginDtoRequest dtoRequest, String cookie) throws ServerException {
        LOGGER.debug("Action \"Login\" by user: {}", dtoRequest.getLogin());
        User user = userDao.getByLogin(dtoRequest.getLogin(), dtoRequest.getPassword());
        // REVU поздно проверять user на null. Вы же его в предыдущей строке уже употребили
        // это надо было до user.setSession делать
        // впрочем, в User Session не нужна
        // впечатление такое, что Вы с этой Session не знаете, что делать
        // нужно было вот что
        // User у Вас тут есть, ему нужно создать сессию
        // Session sessoin = new Session(user, созданный_тут_токен)
        // sessionDao.addSesion(session);
        // логин и есть создание сессии
        // кстати , не мешает убедиться, что у него нет сессии
        // можно сделать вот так
        // https://dev.mysql.com/doc/refman/8.0/en/insert-on-duplicate.html
        if (user == null) {
            LOGGER.debug("Action \"Login\" is failed, user: {} with \"login and password\" not found", dtoRequest.getLogin());
            throw new ServerException(ServerErrorCode.INVALID_LOGIN_OR_PASSWORD);
        }
        userDao.login(user);
        return "COOKIE JAVASESSIONID";
    }

    /**
     * 7.4. Logout
     * DELETE /api/sessions
     *
     * @param cookie
     * @return
     * @throws ServerException
     */
    public EmptyDtoResponse logoutUser(String cookie) throws ServerException {
        LOGGER.debug("Action \"Logout\" for user id: {}", cookie);
        userDao.logout(cookie);
        return new EmptyDtoResponse();
    }

    /**
     * 7.5 Получение информации о текущем пользователе
     * GET /api/accounts
     *
     * @param cookie
     * @return RegisterUserDtoResponse
     * @throws ServerException
     */
    public RegisterUserDtoResponse infoUser(String cookie) throws ServerException {
        LOGGER.debug("Action \"Get info by User\" for user token: {}", cookie);
        User user = userDao.getByToken(cookie);
        if (user == null) {
            throw new ServerException(ServerErrorCode.INVALID_TOKEN);
        }
        return UserMapper.convertUserToRegisterUserDtoResponse(user);
    }

    /**
     * 7.6 Уход пользователя
     * DELETE /api/accounts
     *
     * @param dtoRequest
     * @param cookie
     * @return EmptyDtoResponse
     * @throws ServerException
     */

    public EmptyDtoResponse exitOnServer(String cookie, ExitOnServerDtoRequest dtoRequest) throws ServerException {
        LOGGER.debug("Action \"Get out user on server\" for user by token: {}", cookie);
        User user = userDao.getByToken(cookie);
        if (user.getPassword().equals(dtoRequest.getPassword())) {
            userDao.exitOnServer(cookie);
        } else {
            throw new ServerException(ServerErrorCode.INVALID_LOGIN_OR_PASSWORD);
        }

        return new EmptyDtoResponse();
    }


    /**
     * 7.7. Редактирование профиля пользователя
     * PUT /api/accounts
     *
     * @param dtoRequest
     * @param cookie
     * @return EditAccountDtoResponse
     * @throws ServerException
     */
    public EditAccountDtoResponse editAccount(String cookie, EditAccountDtoRequest dtoRequest) throws ServerException {
        User user = userDao.getByToken(cookie);
        if (user == null) {
            throw new ServerException(ServerErrorCode.INVALID_TOKEN);
        }
        user.setFirstName(dtoRequest.getFirstName());
        user.setLastName(dtoRequest.getLastName());
        user.setPatronymic(dtoRequest.getPatronymic());
        user.setPassword(dtoRequest.getNewPassword());
        userDao.update(user);
        return UserMapper.convertUserToEditAccountDtoResponse(user);
    }

    //ДОДЕЛАТЬ

    /**
     * 7.8. Перевод пользователя в суперпользователи
     * PUT /api/accounts/номер_пользователя/super
     *
     * @param cookie
     * @return
     * @throws ServerException
     */
    public EmptyDtoResponse setUserToSuperUser(String cookie, int userId) throws ServerException {
        //if (userDao.getUserIsSuperUserById()) сделать проверку
        userDao.setUserToSuperUser(userId); //token, login

        return new EmptyDtoResponse();
    }

    /**
     * 7.9. Получение списка пользователей
     * GET /api/accounts
     *
     * @param cookie cookie 	JAVASESSIONID
     *               RequestParam	sortByRating = asc | desc
     *               RequestParam	type = highRating | lowRating | following | followers | ignore | ignoredBy | deleted | super
     *               RequestParam	from = начальный номер
     *               RequestParam	count = количество
     * @return
     * @throws ServerException
     */
    public GetAccountsDtoResponse getAccounts(String cookie, String sortByRating, String type, int from, int count) throws ServerException {
        List<String> usersOnline = userDao.getLoginIsOnline();
        List<String> superUsers = userDao.getLoginIsSuperUser();
        List<User> users;

        // REVU может, сделать один метод маппера с передаваемыми ему параметрами и там <where> <if> ?
        switch (type) {
            case ("highRating"):
                users = userDao.getAccountsHighRating();
            case ("lowRating"):
                users = userDao.getAccountsLowRating();
            case ("following"):
                users = userDao.getFollowingByUserId(Integer.parseInt(cookie));
            case ("followers"):
                users = userDao.getFollowersByUserId(Integer.parseInt(cookie));
            case ("ignore"):
                users = userDao.getIgnoreByUserId(Integer.parseInt(cookie));
            case ("ignoredBy"):
                users = userDao.getIgnoredByUserId(Integer.parseInt(cookie));
            case ("deleted"):
                users = userDao.getUsersExitOnServer();
            case ("super"):
                users = userDao.getAccounts(from, count);
                for (User user : users) {
                    if (!superUsers.contains(user.getLogin())) {
                        users.remove(user);
                    }
                }
            default:
                users = userDao.getAccounts(from, count);
                break;
        }

        if (sortByRating == "asc") {
            users.sort(Comparator.comparingDouble(User::getRating));
        } else if (sortByRating == "desc") {
            users.sort(Comparator.comparingDouble(User::getRating));
            Collections.reverse(users);
        }

//

        return UserMapper.convertUsersListToGetAccountsDtoResponse(users, usersOnline, superUsers);

    }

    /**
     * 7.10 Добавление пользователя в список following
     * POST /api/followings
     *
     * @param cookie
     * @param dtoRequest
     * @return
     * @throws ServerException
     */
    public EmptyDtoResponse addUserToFollowing(String cookie, AddToFollowingListDtoRequest dtoRequest) throws ServerException {
        userDao.addUserToFollowing(Integer.parseInt(cookie), dtoRequest.getLogin()); //userId in cookie
        return new EmptyDtoResponse();
    }

    /**
     * 7.11 Добавление пользователя в список ignore
     * POST /api/ignore
     *
     * @param cookie
     * @param dtoRequest
     * @return
     * @throws ServerException
     */
    public EmptyDtoResponse addUserToIgnore(String cookie, AddToIgnoreListDtoRequest dtoRequest) throws ServerException {
        userDao.addUserToFollowing(Integer.parseInt(cookie), dtoRequest.getLogin()); //userId in cookie
        return new EmptyDtoResponse();
    }

    /**
     * 7.12 Удаление пользователя из списка following
     * DELETE /api/followings/логин_пользователя
     *
     * @param cookie
     * @return
     * @throws ServerException
     */
    public EmptyDtoResponse delUserOnFollowing(String cookie, String login) throws ServerException {
        userDao.delUserOnFollowing(Integer.parseInt(cookie), login); // userId, ignoredLogin
        return new EmptyDtoResponse();
    }

    /**
     * 7.13. Удаление пользователя из списка ignore
     * DELETE /api/ignore/логин_пользователя
     *
     * @param cookie
     * @return
     * @throws ServerException
     */
    public EmptyDtoResponse delUserOnIgnore(String cookie, String login) throws ServerException {
        userDao.delUserOnIgnore(Integer.parseInt(cookie), login); // userId, ignoredLogin
        return new EmptyDtoResponse();
    }


}
