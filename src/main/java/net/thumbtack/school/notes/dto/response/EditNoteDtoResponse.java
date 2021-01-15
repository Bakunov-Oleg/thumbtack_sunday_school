package net.thumbtack.school.notes.dto.response;

import java.time.LocalDateTime;

public class EditNoteDtoResponse {

    private int id;
    private String subject;
    private String body;
    private int sectionId;
    private int authorId;
    private LocalDateTime created;
    private int revisionId;

    public EditNoteDtoResponse(int id, String subject, String body, int sectionId, int authorId, LocalDateTime created, int revisionId) {
        this.id = id;
        this.subject = subject;
        this.body = body;
        this.sectionId = sectionId;
        this.authorId = authorId;
        this.created = created;
        this.revisionId = revisionId;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public int getRevisionId() {
        return revisionId;
    }

    public void setRevisionId(int revisionId) {
        this.revisionId = revisionId;
    }
}
