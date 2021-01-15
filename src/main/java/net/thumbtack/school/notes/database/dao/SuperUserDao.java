package net.thumbtack.school.notes.database.dao;

import net.thumbtack.school.notes.serverexception.ServerException;

public interface SuperUserDao {
    void insert(int userId) throws ServerException;
}
