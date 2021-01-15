package net.thumbtack.school.notes.mappers;

import net.thumbtack.school.notes.UserStatus;
import net.thumbtack.school.notes.database.model.User;
import net.thumbtack.school.notes.dto.request.RegisterUserDtoRequest;
import net.thumbtack.school.notes.dto.response.EditAccountDtoResponse;
import net.thumbtack.school.notes.dto.response.GetAccountsDtoResponse;
import net.thumbtack.school.notes.dto.response.RegisterUserDtoResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// REVU так можно, конечно, но посморите
// https://mapstruct.org/
public class UserMapper {
    public static User convertRegisterUserDtoRequestToUser(RegisterUserDtoRequest dtoRequest) {
        return new User(
                0,
                dtoRequest.getFirstName(),
                dtoRequest.getLastName(),
                dtoRequest.getPatronymic(),
                dtoRequest.getLogin(),
                dtoRequest.getPassword(),
                0.0,
                null,
                null,
                null
        );
    }

    public static RegisterUserDtoResponse convertUserToRegisterUserDtoResponse(User user) {
        return new RegisterUserDtoResponse(
                user.getFirstName(),
                user.getLastName(),
                user.getPatronymic(),
                user.getPassword()
        );
    }

    public static EditAccountDtoResponse convertUserToEditAccountDtoResponse(User user) {
        return new EditAccountDtoResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getPatronymic(),
                user.getLogin()
        );
    }

    public static GetAccountsDtoResponse convertUsersListToGetAccountsDtoResponse(List<User> users, List<String> usersOnline, List<String> superUsers) {
        boolean isDelete;
        boolean isOnline;
        boolean isSuperUser;
        GetAccountsDtoResponse dtoResponse = new GetAccountsDtoResponse();
        List<GetAccountsDtoResponse.UserDto> usersDto = new ArrayList<>();
        for (User user : users) {

            isDelete = user.getUserStatus() != UserStatus.ACTIVE;

            isOnline = usersOnline.contains(user.getLogin());

            isSuperUser = superUsers.contains(user.getLogin());
            usersDto.add(dtoResponse.new UserDto(
                            user.getId(),
                            user.getFirstName(),
                            user.getLastName(),
                            user.getPatronymic(),
                            user.getLogin(),
                            new Date(),
                            isOnline,
                            isDelete,
                            isSuperUser,
                            user.getRating()
                    )
            );
        }

        return new GetAccountsDtoResponse(usersDto);
    }
}

/*
    private String firstName;
    private String lastName;
    private String patronymic;
    private String login;
    private String password;

 */



/*private int id;
    private String firstName;
    private String lastName;
    private String patronymic; //Отчество
    private String login;
    private String password;
    private double rating;
    private Session session;
    private UserStatus userStatus;

 */

