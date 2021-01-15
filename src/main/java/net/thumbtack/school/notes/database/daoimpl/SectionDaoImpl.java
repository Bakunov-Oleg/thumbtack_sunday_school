package net.thumbtack.school.notes.database.daoimpl;

import net.thumbtack.school.notes.database.dao.SectionDao;
import net.thumbtack.school.notes.database.mappers.SectionMapper;
import net.thumbtack.school.notes.database.model.Section;
import net.thumbtack.school.notes.serverexception.ServerErrorCode;
import net.thumbtack.school.notes.serverexception.ServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SectionDaoImpl implements SectionDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(SectionDaoImpl.class);
    private SectionMapper sectionMapper;

    public SectionDaoImpl() {
    }

    @Autowired
    public SectionDaoImpl(SectionMapper sectionMapper) {
        this.sectionMapper = sectionMapper;
    }

    @Override
    public Section insert(String name, int authorId) throws ServerException {
        LOGGER.debug("DAO insert Section, sectionName: {}", name);
        try {
            sectionMapper.insert(name, authorId);
            int sectionId = sectionMapper.getLastInsertId();
            return sectionMapper.getById(sectionId);
        } catch (Exception ex) {
            LOGGER.info("Can't get insert Section by name: {}, {}", name, ex);
            throw new ServerException(ServerErrorCode.SECTION_NAME_IS_USED);
        }
    }

    @Override
    public Section rename(Section section) throws ServerException {
        LOGGER.debug("DAO rename Section, sectionName: {}", section.getName());
        try {
            return sectionMapper.update(section);
        } catch (Exception ex) {
            LOGGER.info("Can't get rename Section by name: {}, {}", section.getName(), ex);
            throw new ServerException(ServerErrorCode.SECTION_NAME_IS_USED);
        }
    }

    @Override
    public void delete(int sectionId) throws ServerException {
        LOGGER.debug("DAO delete Section, sectionId: {}", sectionId);
        try {
            sectionMapper.deleteById(sectionId);
        } catch (Exception ex) {
            LOGGER.info("Can't delete Section by id: {}, {}", sectionId, ex);
            throw new ServerException(ServerErrorCode.SECTION_IS_NOT_FIND);
        }
    }

    @Override
    public Section getInfo(int sectionId) throws ServerException {
        LOGGER.debug("DAO getInfo Section, sectionId: {}", sectionId);
        try {
            return sectionMapper.getById(sectionId);
        } catch (Exception ex) {
            LOGGER.info("Can't getInfo Section by id: {}, {}", sectionId, ex);
            throw new ServerException(ServerErrorCode.SECTION_IS_NOT_FIND);
        }
    }

    @Override
    public List<Section> getAll() throws ServerException {
        LOGGER.debug("DAO all Section");
        try {
            return sectionMapper.getAll();
        } catch (Exception ex) {
            LOGGER.info("Can't getAll Section", ex);
            throw new ServerException(ServerErrorCode.SECTION_IS_NOT_FIND);
        }
    }
}

