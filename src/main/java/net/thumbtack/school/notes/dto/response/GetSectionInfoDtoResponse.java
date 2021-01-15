package net.thumbtack.school.notes.dto.response;

public class GetSectionInfoDtoResponse {

    /*
      “id”: идентификатор раздела,
      "name" : “имя раздела“,
     */

    private int id;
    private String name;

    public GetSectionInfoDtoResponse(int id, String name){
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
