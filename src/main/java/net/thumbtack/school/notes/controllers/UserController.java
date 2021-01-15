package net.thumbtack.school.notes.controllers;

import net.thumbtack.school.notes.dto.request.*;
import net.thumbtack.school.notes.dto.response.EditAccountDtoResponse;
import net.thumbtack.school.notes.dto.response.EmptyDtoResponse;
import net.thumbtack.school.notes.dto.response.GetAccountsDtoResponse;
import net.thumbtack.school.notes.dto.response.RegisterUserDtoResponse;
import net.thumbtack.school.notes.serverexception.ServerException;
import net.thumbtack.school.notes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.UUID;

@RestController
@RequestMapping("/api/accounts")
@Validated
public class UserController {

    private static final String COOKIE_NAME = "JAVASESSIONID";
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /*7.2 Регистрация пользователя.
     *POST /api/accounts */
    @PostMapping(path = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public RegisterUserDtoResponse registerUser(
            @RequestBody RegisterUserDtoRequest dtoRequest,
            HttpServletResponse response
    ) throws ServerException {
        String cookie = UUID.randomUUID().toString();
        RegisterUserDtoResponse dtoResponse = userService.registerUser(cookie, dtoRequest);
        response.addCookie(new Cookie(COOKIE_NAME, cookie));
        return dtoResponse;
    }

    /*7.3. Login
     *POST /api/sessions */
    @PostMapping(path = "/sessions", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void loginUser(
            @RequestBody LoginDtoRequest dtoRequest,
            HttpServletResponse response
    ) throws ServerException {
        String cookie = UUID.randomUUID().toString();
        userService.loginUser(dtoRequest, cookie);
        response.addCookie(new Cookie(COOKIE_NAME, cookie));
    }

    /*7.4. Logout
    DELETE /api/sessions
     */
    @DeleteMapping(path = "/sessions", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmptyDtoResponse logoutUser(
            @CookieValue(COOKIE_NAME) String cookie,
            HttpServletResponse response
    ) throws ServerException {
        EmptyDtoResponse dtoResponse = userService.logoutUser(cookie);
        return dtoResponse;
    }

    /*7.5 Получение информации о текущем пользователе
    GET /api/accounts
     */
    @GetMapping(path = "/account", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public RegisterUserDtoResponse infoUser(
            @CookieValue(COOKIE_NAME) String cookie,
            HttpServletResponse response
    ) throws ServerException {
        return userService.infoUser(cookie);
    }

    /*7.6 Уход пользователя
    DELETE /api/accounts
    */
    @DeleteMapping(path = "/accounts", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public EmptyDtoResponse deleteUser(
            @CookieValue(COOKIE_NAME) String cookie,
            @RequestBody ExitOnServerDtoRequest dtoRequest,
            HttpServletResponse response
    ) throws ServerException {
        return userService.exitOnServer(cookie, dtoRequest);
    }

    /*7.7. Редактирование профиля пользователя
    PUT /api/accounts
     */
    @PutMapping(path = "/accounts", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public EditAccountDtoResponse editUser(
            @CookieValue(COOKIE_NAME) String cookie,
            @RequestBody EditAccountDtoRequest dtoRequest
    ) throws ServerException {
        return userService.editAccount(cookie, dtoRequest);
    }

    /*7.8. Перевод пользователя в суперпользователи
    PUT /api/accounts/номер_пользователя/super
     */
    @PutMapping(path = "/accounts/{userId}/super", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public EmptyDtoResponse editAdminAccount(
            @PathVariable("userId") int userId,
            @CookieValue(COOKIE_NAME) String cookie
    ) throws ServerException {
        return userService.setUserToSuperUser(cookie, userId);
    }

    /*7.9. Получение списка пользователей.
    GET /api/accounts
     */
    @GetMapping(path = "/accounts")
    public GetAccountsDtoResponse getAccounts(
            @CookieValue(COOKIE_NAME) String cookie,
            @RequestParam(name = "sortByRating", defaultValue = "no") String sortByRating,
            @RequestParam(name = "type", defaultValue = "no") String type,
            @RequestParam(name = "from", defaultValue = "0") int from,
            @RequestParam(name = "count", defaultValue = "0") int count
    ) throws ServerException {
        return userService.getAccounts(cookie, sortByRating, type, from, count);
    }

    /*7.10 Добавление пользователя в список following
    POST /api/followings
     */
    @PostMapping(path = "/followings", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmptyDtoResponse addToFollowing(
            @CookieValue(COOKIE_NAME) String cookie,
            @RequestBody AddToFollowingListDtoRequest dtoRequest,
            HttpServletResponse response
    ) throws ServerException {
        userService.addUserToFollowing(cookie, dtoRequest);
        return new EmptyDtoResponse();
    }

    /*7.11 Добавление пользователя в список ignore
    POST /api/ignore
     */
    @PostMapping(path = "/ignore", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmptyDtoResponse addToIgnore(
            @CookieValue(COOKIE_NAME) String cookie,
            @RequestBody AddToIgnoreListDtoRequest dtoRequest,
            HttpServletResponse response
    ) throws ServerException {
        userService.addUserToIgnore(cookie, dtoRequest);
        return new EmptyDtoResponse();
    }

    /*7.12 Удаление пользователя из списка following.
    DELETE /api/followings/логин_пользователя
     */
    @DeleteMapping(path = "/followings/{login}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public EmptyDtoResponse deleteUserOnFollowing(
            @PathVariable("login") String login,
            @CookieValue(COOKIE_NAME) String cookie,
            HttpServletResponse response
    ) throws ServerException {
        return userService.delUserOnFollowing(cookie, login);
    }

    /*7.13. Удаление пользователя из списка ignore
    DELETE /api/ignore/логин_пользователя
     */
    @DeleteMapping(path = "/ignore/{login}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public EmptyDtoResponse deleteOnIgnore(
            @PathVariable("login") String login,
            @CookieValue(COOKIE_NAME) String cookie,
            HttpServletResponse response
    ) throws ServerException {
        return userService.delUserOnIgnore(cookie, login);
    }
}



