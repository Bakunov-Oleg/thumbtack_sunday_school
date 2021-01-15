package net.thumbtack.school.notes.database.daoimpl;

import net.thumbtack.school.notes.database.dao.CommentDao;
import net.thumbtack.school.notes.database.mappers.CommentMapper;
import net.thumbtack.school.notes.database.model.Comment;
import net.thumbtack.school.notes.serverexception.ServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentDaoImpl implements CommentDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentDao.class);

    private CommentMapper commentMapper;

    public CommentDaoImpl() {
    }

    @Autowired
    public CommentDaoImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public Comment insert(Comment comment) throws ServerException {
        return null;
    }

    @Override
    public void updateById(int id, String body) throws ServerException {

    }

    @Override
    public void deleteById(int id) throws ServerException {

    }

    @Override
    public void deleteAllByNoteId(int noteId) throws ServerException {

    }
}
