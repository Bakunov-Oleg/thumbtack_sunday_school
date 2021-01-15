package net.thumbtack.school.notes.dto.request;

public class EditCommentDtoRequest {

    /*
    "body":"текст” // не может быть пустым
     */

    private String body;

    public EditCommentDtoRequest(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
