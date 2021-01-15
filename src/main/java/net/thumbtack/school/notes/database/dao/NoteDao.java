package net.thumbtack.school.notes.database.dao;

import net.thumbtack.school.notes.database.model.Note;
import net.thumbtack.school.notes.dto.response.GetAllNotesDtoResponse;
import net.thumbtack.school.notes.serverexception.ServerException;

import java.time.LocalDateTime;
import java.util.List;

public interface NoteDao {

    Note insert(int authorId, String subject, String body, int sectionId) throws ServerException;

    Note updateOrRepositionById(int id, String body, int sectionId) throws ServerException;

    void deleteById(int id) throws ServerException;

    void deleteAllByAuthorId(int authorId) throws ServerException;

    Note getById(int noteId) throws ServerException;

    List<Note> getByAllWordsInSubject(String words) throws ServerException;

    List<Note> getByOneWordInSubject(String words) throws ServerException;

    List<Note> getByMaxRating() throws ServerException;

    List<Note> getByMinRating() throws ServerException;

    void evaluateRating(int noteId, double rating) throws ServerException;

    List<Note> getAllNotesByAuthorId(int authorId) throws ServerException;

    List<Note> getAllNotesFollowers(int userId) throws ServerException;

    List<Note> getAllNotesIgnoredBy(int userId) throws ServerException;

    List<Note> getAllRevisionNote(int noteId) throws ServerException;

    List<Note> getAllNotes(int sectionId, String sortByRating, String tags, Boolean alltags, LocalDateTime timeFrom, LocalDateTime timeTo, int user, String include, Boolean comments, Boolean allVersions, Boolean commentVersion, int from, int count);
}
