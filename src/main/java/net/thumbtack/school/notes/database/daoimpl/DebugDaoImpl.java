package net.thumbtack.school.notes.database.daoimpl;

import net.thumbtack.school.notes.database.dao.DebugDao;
import net.thumbtack.school.notes.database.mappers.*;
import net.thumbtack.school.notes.serverexception.ServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DebugDaoImpl implements DebugDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(DebugDaoImpl.class);

    private CommentMapper commentMapper;
    private NoteMapper noteMapper;
    private NoteRevisionMapper noteRevisionMapper;
    private SectionMapper sectionMapper;
    private SuperUserMapper superUserMapper;
    private UserMapper userMapper;

    public DebugDaoImpl() {
    }

    @Autowired
    public DebugDaoImpl(CommentMapper commentMapper, NoteMapper noteMapper, NoteRevisionMapper noteRevisionMapper, SectionMapper sectionMapper, SuperUserMapper superUserMapper, UserMapper userMapper) {
        this.commentMapper = commentMapper;
        this.noteMapper = noteMapper;
        this.noteRevisionMapper = noteRevisionMapper;
        this.sectionMapper = sectionMapper;
        this.superUserMapper = superUserMapper;
        this.userMapper = userMapper;
    }


    @Override
    public void clear() throws ServerException {
//        Удаляет с сервера все разделы,  сообщения и примечания и всех пользователей.
        LOGGER.debug("Clear map on database: comment, note, noteRevision, section, superUser, user");
        commentMapper.deleteAll();
        noteMapper.deleteAll();
        noteRevisionMapper.deleteAll();
        sectionMapper.deleteAll();
        superUserMapper.deleteAll();
        userMapper.deleteAll();
    }
}
