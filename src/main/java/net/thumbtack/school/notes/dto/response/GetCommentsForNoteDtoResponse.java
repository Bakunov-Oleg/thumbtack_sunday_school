package net.thumbtack.school.notes.dto.response;

import java.util.Date;
import java.util.List;

public class GetCommentsForNoteDtoResponse {
    /*
        {
      "id": идентификатор_примечания,
      "body": "текст”,
      "noteId": "идентификатор_заметки,
      "authorId": идентификатор_автора,
      "revisionId": номер_ревизии,
      "created": "дата и время создания примечания”
    },
     */

    List<CommentDto> comments;

    public GetCommentsForNoteDtoResponse(List<CommentDto> comments) {
        this.comments = comments;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }

    public class CommentDto{
        private int id;
        private String body;
        private int noteId;
        private int authorId;
        private int revisionId;
        private Date created;

        public CommentDto(int id, String body, int noteId, int authorId, int revisionId, Date created) {
            this.id = id;
            this.body = body;
            this.noteId = noteId;
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

        public int getNoteId() {
            return noteId;
        }

        public void setNoteId(int noteId) {
            this.noteId = noteId;
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
}
