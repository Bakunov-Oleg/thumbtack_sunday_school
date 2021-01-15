package net.thumbtack.school.notes.database.dao;

import net.thumbtack.school.notes.serverexception.ServerException;

public interface DebugDao {
    void clear() throws ServerException;
}
