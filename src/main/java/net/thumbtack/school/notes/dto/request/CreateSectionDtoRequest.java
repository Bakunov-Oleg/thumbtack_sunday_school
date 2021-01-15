package net.thumbtack.school.notes.dto.request;

/*
"name":"название раздела”
 */

public class CreateSectionDtoRequest {
    private String name;

    public CreateSectionDtoRequest(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
