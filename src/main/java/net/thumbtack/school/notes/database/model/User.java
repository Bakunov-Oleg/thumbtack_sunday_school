package net.thumbtack.school.notes.database.model;

import net.thumbtack.school.notes.UserStatus;

import java.time.LocalDateTime;
import java.util.List;

public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String patronymic; //Отчество
    private String login;
    private String password;
    private double rating;
    private UserStatus userStatus;
    // REVU лучше LocalDate, а то и LocalDateTime
    // в БД у Вас `createDate` DATETIME NOT NULL,
    // совместимо с LocalDateTime
    private LocalDateTime createDate;
    // REVU а можно еще List<Note>
    private List<Note> notes;

    public User() {
    }

    public User(int id, String firstName, String lastName, String patronymic, String login, String password, double rating, UserStatus userStatus, LocalDateTime createDate, List<Note> notes) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.login = login;
        this.password = password;
        this.rating = rating;
        this.userStatus = userStatus;
        this.createDate = createDate;
        this.notes = notes;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
}
