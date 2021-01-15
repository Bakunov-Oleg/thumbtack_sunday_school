package net.thumbtack.school.notes.dto.response;

import net.thumbtack.school.notes.database.model.Section;

import java.util.List;

public class GetSectionsDtoResponse {

    /*
    {
      “id”: идентификатор раздела,
      "name" : “имя раздела“
    },
     */

    List<SectionDto> sections;

    public GetSectionsDtoResponse(List<SectionDto> sections) {
        this.sections = sections;
    }

    public List<SectionDto> getSections() {
        return sections;
    }

    public void setSections(List<SectionDto> sections) {
        this.sections = sections;
    }

    public class SectionDto{

        private int id;
        private String name;

        public SectionDto(int id, String name) {
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
}
