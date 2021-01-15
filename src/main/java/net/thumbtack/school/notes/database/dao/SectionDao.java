package net.thumbtack.school.notes.database.dao;

import net.thumbtack.school.notes.database.model.Section;
import net.thumbtack.school.notes.serverexception.ServerException;

import java.util.List;

public interface SectionDao {

    Section insert(String name, int authorId) throws ServerException;

    Section rename(Section section) throws ServerException;

    void delete(int sectionId) throws ServerException;

    Section getInfo(int sectionId) throws ServerException;

    List<Section> getAll() throws ServerException;


}
