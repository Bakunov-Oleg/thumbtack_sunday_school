package net.thumbtack.school.notes.database.daoimpl;

import net.thumbtack.school.notes.database.dao.SessionDao;
import net.thumbtack.school.notes.database.mappers.SessionMapper;
import net.thumbtack.school.notes.database.model.Session;
import net.thumbtack.school.notes.serverexception.ServerErrorCode;
import net.thumbtack.school.notes.serverexception.ServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Component
public class SessionDaoImpl implements SessionDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionDaoImpl.class);

    private SessionMapper sessionMapper;

    public SessionDaoImpl() {
    }

    @Autowired
    public SessionDaoImpl(SessionMapper sessionMapper) {
        this.sessionMapper = sessionMapper;
    }

    @Override
    public Session insert(Session session) throws ServerException {
        LOGGER.debug("DAO insert Session");
        try {
            int id = sessionMapper.insert(session);
            session.setId(id);
            return session;
        } catch (DataAccessException ex) {
            LOGGER.info("Can't insert session {}", ex);
            throw new ServerException(ServerErrorCode.INVALID_LOGIN_OR_PASSWORD);
        }

    }

    @Override
    public void update(Session session) throws ServerException {
        LOGGER.debug("DAO update Session");
        try {
            sessionMapper.update(session);
        } catch (DataAccessException ex) {
            LOGGER.info("Can't insert session {}", ex);
            throw new ServerException(ServerErrorCode.INVALID_LOGIN_OR_PASSWORD);
        }
    }

    @Override
    public String getTokenByUserId(int userId) throws ServerException {
        LOGGER.debug("DAO get token by userId: {}", userId);
        try {
            return sessionMapper.getTokenByUserId(userId);
        } catch (DataAccessException ex) {
            LOGGER.info("Can't get token by userId {} {} ", userId, ex);
            throw new ServerException(ServerErrorCode.INVALID_LOGIN_OR_PASSWORD);
        }
    }

    @Override
    public int getUserIdByToken(String token) throws ServerException {
        LOGGER.debug("DAO get userId by token: {}", token);
        try {
            return sessionMapper.getUserIdByToken(token);
        } catch (DataAccessException ex) {
            LOGGER.info("Can't get userId by token: {} {} ", token, ex);
            throw new ServerException(ServerErrorCode.INVALID_LOGIN_OR_PASSWORD);
        }
    }

    @Override
    public Session getSessionByToken(String token) throws ServerException {
        LOGGER.debug("DAO get Session by token: {}", token);
        try {
            return sessionMapper.getByToken(token);
        } catch (DataAccessException ex) {
            LOGGER.info("Can't get Session by token: {} {} ", token, ex);
            throw new ServerException(ServerErrorCode.INVALID_LOGIN_OR_PASSWORD);
        }
    }

    @Override
    public Session getById(int id) throws ServerException {
        LOGGER.debug("DAO get Session by id: {}", id);
        try {
            return sessionMapper.getById(id);
        } catch (DataAccessException ex) {
            LOGGER.info("Can't get Session by id: {} {} ", id, ex);
            throw new ServerException(ServerErrorCode.INVALID_LOGIN_OR_PASSWORD);
        }
    }

    @Override
    public void deleteByUserId(int userId) throws ServerException {
        LOGGER.debug("DAO delete Session by userId: {}", userId);
        try {
            sessionMapper.deleteByUserId(userId);
        } catch (DataAccessException ex) {
            LOGGER.info("Can't delete Session by userId: {} {} ", userId, ex);
            throw new ServerException(ServerErrorCode.INVALID_LOGIN_OR_PASSWORD);
        }
    }
}
