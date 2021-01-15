package net.thumbtack.school.notes.database.model;

import java.util.List;

public class Section {

    private int id;
    private String name;
    private User author;
    private List<Note> notes;

    public Section() {
    }

    public Section(int id, String name, User author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }

    public Section(String name, User author) {
        this.name = name;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
}
