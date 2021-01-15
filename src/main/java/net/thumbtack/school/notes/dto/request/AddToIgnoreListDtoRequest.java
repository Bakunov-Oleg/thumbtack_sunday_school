package net.thumbtack.school.notes.dto.request;

/*
"login": "логин"
 */

public class AddToIgnoreListDtoRequest {
    private String login;

    public AddToIgnoreListDtoRequest (String login){
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
