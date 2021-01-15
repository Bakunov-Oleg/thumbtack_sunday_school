package net.thumbtack.school.notes.dto.response;

import java.util.Date;
import java.util.List;

public class GetAccountsDtoResponse {

    /*
        "id": идентификатор пользователя,
        "firstName" : “имя“,
        "lastName" : “фамилия“,
        "patronymic" : “отчество“,
        "login" : “логин“,
        "timeRegistered" : ”время и дата его регистрации”,
        "online" : true | false, // online ли сейчас
        "deleted" : true | false, // ушел ли с сервера
        "super" : true | false, // является ли суперпользователем
        "rating" :  “рейтинг”
     */

    List<UserDto> users;

    public GetAccountsDtoResponse() {

    }

    public GetAccountsDtoResponse(List<UserDto> users) {
        this.users = users;
    }

    public List<UserDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
    }

    public class UserDto {
        private int id;
        private String firstName;
        private String lastName;
        private String patronymic;
        private String login;
        private Date timeRegistered;
        private Boolean online;
        private Boolean deleted;
        private boolean superUser;
        private double rating;

        public UserDto(int id, String firstName, String lastName, String patronymic, String login, Date timeRegistered, Boolean online, Boolean deleted, boolean superUser, double rating) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.patronymic = patronymic;
            this.login = login;
            this.timeRegistered = timeRegistered;
            this.online = online;
            this.deleted = deleted;
            this.superUser = superUser;
            this.rating = rating;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public Date getTimeRegistered() {
            return timeRegistered;
        }

        public void setTimeRegistered(Date timeRegistered) {
            this.timeRegistered = timeRegistered;
        }

        public Boolean getOnline() {
            return online;
        }

        public void setOnline(Boolean online) {
            this.online = online;
        }

        public Boolean getDeleted() {
            return deleted;
        }

        public void setDeleted(Boolean deleted) {
            this.deleted = deleted;
        }

        public boolean isSuperUser() {
            return superUser;
        }

        public void setSuperUser(boolean superUser) {
            this.superUser = superUser;
        }

        public double getRating() {
            return rating;
        }

        public void setRating(double rating) {
            this.rating = rating;
        }
    }

}
