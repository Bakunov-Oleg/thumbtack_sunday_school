package net.thumbtack.school.notes.dto.request;

/*
"name":"новое название раздела”
*/

public class RenameSectionDtoRequest {
    private String name;

    public RenameSectionDtoRequest(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
