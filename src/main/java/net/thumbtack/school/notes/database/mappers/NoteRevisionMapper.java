package net.thumbtack.school.notes.database.mappers;

import net.thumbtack.school.notes.database.model.NoteRevision;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface NoteRevisionMapper {

    @Insert("INSERT INTO `notes`.`note_revision` ( `noteId`, `revisionId`, `body`, `created`) "
            + "VALUES ( #{noteId}, #{revisionId}, #{body}, #{created} );")
    void insert(@Param("noteId") int noteId, @Param("revisionId") int revisionId, @Param("body") String body, @Param("created") LocalDateTime created);

    @Select("SELECT `body` FROM `notes`.`note_revision` WHERE `note_revision`.`noteId` = #{noteId} ;")
    String getBodyByNoteId(@Param("noteId") int noteId);

    @Select("SELECT * FROM `notes`.`note_revision` WHERE `note_revision`.`noteId` = #{noteId} ;")
    List<NoteRevision> getAllRevisionsByNote(@Param("noteId") int noteId);


    @Select("SELECT * FROM `notes`.`note_revision` WHERE `note_revision`.`noteId` = #{noteId} ;")
    NoteRevision getByNoteId(@Param("noteId") int noteId);

    @Delete("DELETE FROM `notes`.`note_revision` WHERE noteId = #{noteId})")
    void delete(@Param("noteId") int noteId);

    @Delete("DELETE FROM `notes`.`note_revision`;")
    void deleteAll();
}
