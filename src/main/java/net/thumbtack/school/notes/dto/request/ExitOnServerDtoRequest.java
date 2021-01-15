package net.thumbtack.school.notes.dto.request;

public class ExitOnServerDtoRequest {

    /*
    "password": "текущий пароль пользователя"
     */

    private String password;

    public ExitOnServerDtoRequest(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
