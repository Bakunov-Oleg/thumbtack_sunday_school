package net.thumbtack.school.notes.database.dao;

import net.thumbtack.school.notes.database.model.Comment;
import net.thumbtack.school.notes.serverexception.ServerException;


public interface CommentDao {

    Comment insert(Comment comment) throws ServerException;

    void updateById(int id, String body) throws ServerException;

    void deleteById(int id) throws ServerException;

    void deleteAllByNoteId(int noteId) throws ServerException;

}
