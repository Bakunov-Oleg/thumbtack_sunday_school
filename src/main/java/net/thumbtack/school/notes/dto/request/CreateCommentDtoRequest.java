package net.thumbtack.school.notes.dto.request;

public class CreateCommentDtoRequest {

 /*
 "body": "текст”,// не может быть пустым
 "noteId": идентификатор_заметки
 */

    private String body;
    private int noteId;

    public CreateCommentDtoRequest(String body, int noteId) {
        this.body = body;
        this.noteId = noteId;
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
}
