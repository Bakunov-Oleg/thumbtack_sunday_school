package net.thumbtack.school.notes.dto.request;

/*
        "password": "текущий пароль пользователя"
 */

public class DeleteAccountDtoRequest {

    private String password;

    public DeleteAccountDtoRequest(String password){
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
