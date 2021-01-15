package net.thumbtack.school.notes.database.model;

import java.util.Objects;

public class Session {

    private int id;
    private int userId;
    private String token;

    public Session() {
    }

    public Session(String token) {
        this.token = token;
    }

    public Session(int userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return userId == session.userId &&
                Objects.equals(token, session.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, token);
    }
}
