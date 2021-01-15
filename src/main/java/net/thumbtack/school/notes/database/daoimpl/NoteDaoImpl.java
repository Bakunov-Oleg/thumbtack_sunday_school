package net.thumbtack.school.notes.database.daoimpl;

import net.thumbtack.school.notes.database.dao.NoteDao;
import net.thumbtack.school.notes.database.mappers.NoteMapper;
import net.thumbtack.school.notes.database.mappers.NoteRevisionMapper;
import net.thumbtack.school.notes.database.model.Note;
import net.thumbtack.school.notes.serverexception.ServerErrorCode;
import net.thumbtack.school.notes.serverexception.ServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class NoteDaoImpl implements NoteDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

    private NoteMapper noteMapper;
    private NoteRevisionMapper noteRevisionMapper;

    public NoteDaoImpl() {
    }

    @Autowired
    public NoteDaoImpl(NoteMapper noteMapper, NoteRevisionMapper noteRevisionMapper) {
        this.noteMapper = noteMapper;
        this.noteRevisionMapper = noteRevisionMapper;
    }

    @Override
    public Note insert(int authorId, String subject, String body, int sectionId) throws ServerException {
        LOGGER.debug("DAO insert Note by subject: {}", subject);
        try {
            noteMapper.insert(authorId, subject, sectionId, 0.0);
            int noteId = noteMapper.getLastInsertId();
            noteRevisionMapper.insert(noteId, 1, body, LocalDateTime.now());
            Note note = noteMapper.getNoteById(noteId);
            return note;
        } catch (DataAccessException ex) {
            LOGGER.info("Can't insert Note by subject: {}", subject, ex);
            throw new ServerException(ServerErrorCode.INVALID_LOGIN_OR_PASSWORD);
        }

    }

    @Override
    public Note updateOrRepositionById(int noteId, String body, int sectionId) throws ServerException {
        LOGGER.debug("DAO update or reposition Note by noteId: {}", noteId);
        try {
            if (sectionId != 0) {
                noteMapper.reposition(noteId, sectionId);
            }
            if (body != null) {
                Note note = noteMapper.getNoteById(noteId);
                int noteRevisionNumber = note.getRevisions().size() + 1;
                noteRevisionMapper.insert(noteId, noteRevisionNumber, body, LocalDateTime.now());
            }
        } catch (DataAccessException ex) {
            LOGGER.info("Can't update or reposition Note by noteId: {}", noteId, ex);
            throw new ServerException(ServerErrorCode.INVALID_LOGIN_OR_PASSWORD);
        }
        return noteMapper.getNoteById(noteId);
    }

    @Override
    public void deleteById(int noteId) throws ServerException {
        LOGGER.debug("DAO delete Note by noteId: {}", noteId);
        try {
            noteMapper.deleteById(noteId);
        } catch (DataAccessException ex) {
            LOGGER.info("Can't delete Note by noteId: {}", noteId, ex);
            throw new ServerException(ServerErrorCode.INVALID_LOGIN_OR_PASSWORD);
        }
    }

    @Override
    public void deleteAllByAuthorId(int authorId) throws ServerException {

    }

    @Override
    public Note getById(int noteId) throws ServerException {
        LOGGER.debug("DAO get Note by noteId: {}", noteId);
        try {
            return noteMapper.getNoteById(noteId);
        } catch (DataAccessException ex) {
            LOGGER.info("Can't get Note by noteId: {}", noteId, ex);
            throw new ServerException(ServerErrorCode.INVALID_LOGIN_OR_PASSWORD);
        }
    }

    @Override
    public List<Note> getByAllWordsInSubject(String words) throws ServerException {
        return null;
    }

    @Override
    public List<Note> getByOneWordInSubject(String words) throws ServerException {
        return null;
    }

    @Override
    public List<Note> getByMaxRating() throws ServerException {
        return null;
    }

    @Override
    public List<Note> getByMinRating() throws ServerException {
        return null;
    }

    @Override
    public void evaluateRating(int noteId, double rating) throws ServerException {
        LOGGER.debug("DAO evaluate rating Note by noteId: {}", noteId);
        try {
            Double currentRating;
            currentRating = noteMapper.getRatingByNoteId(noteId);
            noteMapper.evaluateRating(noteId, (currentRating + rating) / 2);
        } catch (DataAccessException ex) {
            LOGGER.info("Can't evaluate rating Note by noteId: {}", noteId, ex);
            throw new ServerException(ServerErrorCode.INVALID_LOGIN_OR_PASSWORD);
        }
    }

    @Override
    public List<Note> getAllNotesByAuthorId(int authorId) throws ServerException {
        return null;
    }

    @Override
    public List<Note> getAllNotesFollowers(int userId) throws ServerException {
        return null;
    }

    @Override
    public List<Note> getAllNotesIgnoredBy(int userId) throws ServerException {
        return null;
    }

    @Override
    public List<Note> getAllRevisionNote(int noteId) throws ServerException {
        return null;
    }

    @Override
    public List<Note> getAllNotes(int sectionId, String sortByRating, String tags, Boolean alltags, LocalDateTime timeFrom, LocalDateTime timeTo, int user, String include, Boolean comments, Boolean allVersions, Boolean commentVersion, int from, int count) {
        return null;
    }
}
