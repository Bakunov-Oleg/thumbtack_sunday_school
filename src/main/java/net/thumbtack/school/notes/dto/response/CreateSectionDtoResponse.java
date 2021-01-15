package net.thumbtack.school.notes.dto.response;

/*
    "id":"идентификатор раздела,
    "name":"название раздела”
 */

public class CreateSectionDtoResponse {

    private int id;
    private String name;

    public CreateSectionDtoResponse(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

