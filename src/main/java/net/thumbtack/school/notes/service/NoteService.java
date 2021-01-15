package net.thumbtack.school.notes.service;

import net.thumbtack.school.notes.ApplicationProperties;
import net.thumbtack.school.notes.database.dao.NoteDao;
import net.thumbtack.school.notes.database.model.Note;
import net.thumbtack.school.notes.dto.request.CreateNoteDtoRequest;
import net.thumbtack.school.notes.dto.request.EditNoteDtoRequest;
import net.thumbtack.school.notes.dto.response.CreateNoteDtoResponse;
import net.thumbtack.school.notes.dto.response.EditNoteDtoResponse;
import net.thumbtack.school.notes.dto.response.GetAllNotesDtoResponse;
import net.thumbtack.school.notes.dto.response.GetNoteInfoDtoResponse;
import net.thumbtack.school.notes.mappers.NoteMapper;
import net.thumbtack.school.notes.serverexception.ServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional(rollbackFor = DataAccessException.class)
public class NoteService {
    private static final Logger LOGGER = LoggerFactory.getLogger(NoteService.class);
    private final NoteDao noteDao;



    @Autowired
    public NoteService(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    public CreateNoteDtoResponse create(String cookie, CreateNoteDtoRequest dtoRequest) throws ServerException {
        LOGGER.debug("Action \"Create note\" by subject: {}", dtoRequest.getSubject());
        Note note = noteDao.insert(Integer.parseInt(cookie), dtoRequest.getSubject(), dtoRequest.getBody(), dtoRequest.getSectionId());
        return NoteMapper.INSTANSE.noteToCreateNoteDtoResponse(note);
    }

    public GetNoteInfoDtoResponse getInfo(int noteId) throws ServerException {
        LOGGER.debug("Action \"Get info note\" by noteId: {}", noteId);
        Note note = noteDao.getById(noteId);
        return NoteMapper.INSTANSE.noteToGetNoteInfoDtoResponse(note);
    }

    public EditNoteDtoResponse editOrReposition(String cookie, EditNoteDtoRequest dtoRequest, int noteId) throws ServerException {
        LOGGER.debug("Action \"Get info note\" by noteId: {}", noteId);
        Note note = noteDao.updateOrRepositionById(noteId, dtoRequest.getBody(), dtoRequest.getSectionId());
        return NoteMapper.INSTANSE.noteToEditNoteDtoResponse(note);
    }

    public void delete(String cookie, int noteId) throws ServerException {
        LOGGER.debug("Action \"Delete note\" by noteId: {}", noteId);
        noteDao.deleteById(noteId);
    }

    public void evaluateRating(int noteId, int rating) throws ServerException {
        LOGGER.debug("Action \"Evaluate rating note\" by noteId: {}", noteId);
        noteDao.evaluateRating(noteId, rating);
    }

    public GetAllNotesDtoResponse getAll(String cookie, int sectionId, String sortByRating, String tags, Boolean alltags, LocalDateTime timeFrom, LocalDateTime timeTo, int user, String include, Boolean comments, Boolean allVersions, Boolean commentVersion, int from, int count) {
        LOGGER.debug("Action \"Get all notes\"");
        noteDao.getAllNotes(sectionId, sortByRating, tags, alltags, timeFrom, timeTo, user, include, comments, allVersions, commentVersion, from, count);
        return null;
    }
}
