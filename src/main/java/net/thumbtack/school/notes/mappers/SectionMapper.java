package net.thumbtack.school.notes.mappers;

import net.thumbtack.school.notes.database.model.Section;
import net.thumbtack.school.notes.dto.response.CreateSectionDtoResponse;
import net.thumbtack.school.notes.dto.response.GetSectionInfoDtoResponse;
import net.thumbtack.school.notes.dto.response.GetSectionsDtoResponse;
import net.thumbtack.school.notes.dto.response.RenameSectionDtoResponse;
import org.apache.ibatis.annotations.Mapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

//@Mapper
public interface SectionMapper {
    SectionMapper INSTANSE = Mappers.getMapper(SectionMapper.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name")
    })
    CreateSectionDtoResponse sectionToCreateSectionDtoResponse(Section section);
//    @InheritInverseConfiguration
//    PrimaryContact businessToPrimaryContact(BusinessContact business);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name")
    })
    RenameSectionDtoResponse sectionToRenameSectionDtoResponse(Section section);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name")
    })
    GetSectionInfoDtoResponse sectionToGetSectionInfoDtoResponse(Section section);

    @Mappings({
            @Mapping(source = "sections", target = "sections")
    })
    GetSectionsDtoResponse sectionsToGetSectionsDtoResponse(List<Section> sections);

}
