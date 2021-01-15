package net.thumbtack.school.notes.dto.request;

/*
    "firstName": "имя",
    "lastName": "фамилия",
    "patronymic": "отчество", // необязателен
    "oldPassword": "старый пароль",
    "newPassword": "новый пароль"

 */

public class EditAccountDtoRequest {

    private String firstName;
    private String lastName;
    private String patronymic;
    private String oldPassword;
    private String newPassword;

    public EditAccountDtoRequest(String firstName, String lastName, String oldPassword, String newPassword){
        this.firstName = firstName;
        this.lastName = lastName;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public EditAccountDtoRequest(String firstName, String lastName, String patronymic, String oldPassword, String newPassword){
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
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

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
