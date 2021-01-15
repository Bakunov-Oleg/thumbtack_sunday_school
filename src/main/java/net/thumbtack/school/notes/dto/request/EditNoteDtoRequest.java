package net.thumbtack.school.notes.dto.request;

public class EditNoteDtoRequest {

    /*
    "body":"текст”, // не может быть пустым
    "sectionId": идентификатор_раздела,
     */

    private String body;
    private int sectionId;

    public EditNoteDtoRequest(String body) {
        this.body = body;
    }

    public EditNoteDtoRequest(int sectionId) {
        this.sectionId = sectionId;
    }

    public EditNoteDtoRequest(String body, int sectionId) {
        this.body = body;
        this.sectionId = sectionId;
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
}
