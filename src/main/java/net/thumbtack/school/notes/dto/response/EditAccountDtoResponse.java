package net.thumbtack.school.notes.dto.response;

/*
        "id": идентификатор пользователя,
        "firstName": "имя",
        "lastName": "фамилия",
        "patronymic": "отчество",
        "login": "логин"

 */

import net.thumbtack.school.notes.dto.request.EditAccountDtoRequest;

public class EditAccountDtoResponse {

    private int id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String login;

    public EditAccountDtoResponse(int id, String firstName, String lastName, String patronymic, String login){
        this.id = id;
        this. firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.login = login;
    }
}
