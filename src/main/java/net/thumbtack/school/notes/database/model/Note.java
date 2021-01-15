package net.thumbtack.school.notes.database.model;

import java.util.List;

public class Note {
    private int id;
    private String subject;
    private Section section;
    private User author;
    private double rating;
    private List<Comment> comments;
    private List<NoteRevision> revisions;

    public Note() {
    }

    public Note(int id, String subject, Section section, User author, double rating, List<Comment> comments, List<NoteRevision> revisions) {
        this.id = id;
        this.subject = subject;
        this.section = section;
        this.author = author;
        this.rating = rating;
        this.comments = comments;
        this.revisions = revisions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<NoteRevision> getRevisions() {
        return revisions;
    }

    public void setRevisions(List<NoteRevision> revisions) {
        this.revisions = revisions;
    }
}

