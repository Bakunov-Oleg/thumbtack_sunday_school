package net.thumbtack.school.notes.dto.response;

/*
        "firstName": "имя",
        "lastName": "фамилия",
        "patronymic": "отчество",
        "login": "логин"

 */

public class GetUserInfoDtoResponse {
    private String firstName;
    private String lastName;
    private String patronymic;
    private String login;

    public GetUserInfoDtoResponse(String firstName, String lastName, String patronymic, String login){
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
