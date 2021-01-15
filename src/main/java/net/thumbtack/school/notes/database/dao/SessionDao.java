package net.thumbtack.school.notes.database.dao;

import net.thumbtack.school.notes.database.model.Session;
import net.thumbtack.school.notes.serverexception.ServerException;

public interface SessionDao {

    Session insert(Session session) throws ServerException;

    void update(Session session) throws ServerException;

    String getTokenByUserId(int userId) throws ServerException;

    int getUserIdByToken(String token) throws ServerException;

    Session getSessionByToken(String token) throws ServerException;

    Session getById(int id) throws ServerException;

    void deleteByUserId (int userId) throws ServerException;


}
