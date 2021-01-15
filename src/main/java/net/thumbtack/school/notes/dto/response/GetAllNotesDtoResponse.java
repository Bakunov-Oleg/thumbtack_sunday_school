package net.thumbtack.school.notes.dto.response;

import java.util.Date;
import java.util.List;

public class GetAllNotesDtoResponse {
    /*
    {
      "id": идентификатор_заметки,
      "subject": "заголовок”,
      "body":"текст”
      "sectionId": идентификатор_раздела,
      "authorId": идентификатор_автора,
      "created": "дата и время создания заметки”,

      "revisions" : [
        {
          “id”: номер_ревизии,
          "body" : “имя раздела“,
          "created": "дата и время создания заметки”,
          "comments": [
            {
              "id": идентификатор_примечания,
              "body": "текст”,
              "authorId": идентификатор_автора,
              "revisionId": номер_ревизии,
              "created": "дата и время создания комментария”,
            },
     */

    private List<RevisionDto> revisions;

    public GetAllNotesDtoResponse(List<RevisionDto> revisions) {
        this.revisions = revisions;
    }

    public List<RevisionDto> getRevisions() {
        return revisions;
    }

    public void setRevisions(List<RevisionDto> revisions) {
        this.revisions = revisions;
    }

    public class CommentDto{
        private int id;
        private String body;
        private int authorId;
        private int revisionId;
        private Date created;

        public CommentDto(int id, String body, int authorId, int revisionId, Date created) {
            this.id = id;
            this.body = body;
            this.authorId = authorId;
            this.revisionId = revisionId;
            this.created = created;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public int getAuthorId() {
            return authorId;
        }

        public void setAuthorId(int authorId) {
            this.authorId = authorId;
        }

        public int getRevisionId() {
            return revisionId;
        }

        public void setRevisionId(int revisionId) {
            this.revisionId = revisionId;
        }

        public Date getCreated() {
            return created;
        }

        public void setCreated(Date created) {
            this.created = created;
        }
    }

    public class RevisionDto{
        private int id;
        private String body;
        private Date created;
        private List<CommentDto> comments;

        public RevisionDto(int id, String body, Date created, List<CommentDto> comments) {
            this.id = id;
            this.body = body;
            this.created = created;
            this.comments = comments;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public Date getCreated() {
            return created;
        }

        public void setCreated(Date created) {
            this.created = created;
        }

        public List<CommentDto> getComments() {
            return comments;
        }

        public void setComments(List<CommentDto> comments) {
            this.comments = comments;
        }
    }

    public class NoteDto{
        private int id;
        private String subject;
        private String body;
        private int sectionId;
        private int authorId;
        private Date created;
        private List<RevisionDto> revisions;

        public NoteDto(int id, String subject, String body, int sectionId, int authorId, Date created, List<RevisionDto> revisions) {
            this.id = id;
            this.subject = subject;
            this.body = body;
            this.sectionId = sectionId;
            this.authorId = authorId;
            this.created = created;
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

        public List<RevisionDto> getRevisions() {
            return revisions;
        }

        public void setRevisions(List<RevisionDto> revisions) {
            this.revisions = revisions;
        }
    }
}
