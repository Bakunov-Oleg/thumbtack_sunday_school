package net.thumbtack.school.notes.dto.request;

public class CreateNoteDtoRequest {

    /*
    "subject":"заголовок”,
    "body":"текст”,
    "sectionId": идентификатор_раздела

     */

    private String subject;
    private String body;
    private int sectionId;

    public CreateNoteDtoRequest(String subject, String body, int sectionId){
        this.subject = subject;
        this.body = body;
        this.sectionId = sectionId;
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
}
