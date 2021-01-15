package net.thumbtack.school.notes.dto.request;

/*
"login": "логин"
*/

public class AddToFollowingListDtoRequest {

    private String login;

    public AddToFollowingListDtoRequest (String login){
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
