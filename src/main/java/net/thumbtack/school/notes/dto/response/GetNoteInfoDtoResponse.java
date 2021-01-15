package net.thumbtack.school.notes.dto.response;

import java.util.Date;

public class GetNoteInfoDtoResponse {

    /*
    "id":"идентификатор заметки,
    "subject": "заголовок”,
    "body":"текст”
    "sectionId": идентификатор_раздела,
    "authorId": идентификатор_автора,
    "created": "дата и время создания заметки”,
    "revisionId": номер_ревизии
     */

    private int id;
    private String subject;
    private String body;
    private int sectionId;
    private int authorId;
    private Date created;
    private int revisionId;

    public GetNoteInfoDtoResponse(int id, String subject, String body, int sectionId, int authorId, Date created, int revisionId) {
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getRevisionId() {
        return revisionId;
    }

    public void setRevisionId(int revisionId) {
        this.revisionId = revisionId;
    }
}
