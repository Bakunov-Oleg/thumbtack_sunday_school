package net.thumbtack.school.notes.mappers;

import net.thumbtack.school.notes.database.model.Note;
import net.thumbtack.school.notes.dto.response.CreateNoteDtoResponse;
import net.thumbtack.school.notes.dto.response.EditNoteDtoResponse;
import net.thumbtack.school.notes.dto.response.GetNoteInfoDtoResponse;
import org.apache.ibatis.annotations.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

//@Mapper
public interface NoteMapper {

    NoteMapper INSTANSE = Mappers.getMapper(NoteMapper.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "subject", target = "subject"),
            @Mapping(source = "!!!", target = "body"), //как получить послежнюю ревизию и из нее вытащить
            @Mapping(source = "section.id", target = "sectionId"),
            @Mapping(source = "author.id", target = "authorId"),
            @Mapping(source = "!!!", target = "created"), //как получить послежнюю ревизию и из нее вытащить
            @Mapping(source = "!!!", target = "revisionId")  //как получить послежнюю ревизию и из нее вытащить
    })
    CreateNoteDtoResponse noteToCreateNoteDtoResponse(Note note);
    GetNoteInfoDtoResponse noteToGetNoteInfoDtoResponse(Note note);
    EditNoteDtoResponse noteToEditNoteDtoResponse (Note note);

//    @InheritInverseConfiguration
//    PrimaryContact businessToPrimaryContact(BusinessContact business);
}


