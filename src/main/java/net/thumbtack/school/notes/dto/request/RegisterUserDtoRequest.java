package net.thumbtack.school.notes.dto.request;

/*
        "firstName": "имя",
        "lastName": "фамилия",
        "patronymic": "отчество", // необязателен
        "login": "логин",
        "password": "пароль"
*/

public class RegisterUserDtoRequest {
    private String firstName;
    private String lastName;
    private String patronymic;
    private String login;
    private String password;

    public RegisterUserDtoRequest(String firstName, String lastName, String login, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
    }

    public RegisterUserDtoRequest(String firstName, String lastName, String patronymic, String login, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.login = login;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
