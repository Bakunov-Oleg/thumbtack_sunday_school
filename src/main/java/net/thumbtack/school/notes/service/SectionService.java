package net.thumbtack.school.notes.service;

import net.thumbtack.school.notes.ApplicationProperties;
import net.thumbtack.school.notes.database.dao.SectionDao;
import net.thumbtack.school.notes.database.dao.UserDao;
import net.thumbtack.school.notes.database.model.Section;
import net.thumbtack.school.notes.dto.request.CreateSectionDtoRequest;
import net.thumbtack.school.notes.dto.request.RenameSectionDtoRequest;
import net.thumbtack.school.notes.dto.response.*;
import net.thumbtack.school.notes.mappers.SectionMapper;
import net.thumbtack.school.notes.serverexception.ServerErrorCode;
import net.thumbtack.school.notes.serverexception.ServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = DataAccessException.class)
public class SectionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final SectionDao sectionDao;
    private final UserDao userDao;

    @Autowired
    ApplicationProperties properties;

    @Autowired
    public SectionService(SectionDao sectionDao, UserDao userDao) {
        this.sectionDao = sectionDao;
        this.userDao = userDao;
    }


    /**
     * 7.14. Создание раздела
     * POST /api/sections
     *
     * @param cookie
     * @param dtoRequest
     * @return CreateSectionDtoResponse
     * @throws ServerException
     */
    public CreateSectionDtoResponse createSection(String cookie, CreateSectionDtoRequest dtoRequest) throws ServerException {
        LOGGER.debug("Action \"Create section\" name: {}", dtoRequest.getName());
        Section section = sectionDao.insert(dtoRequest.getName(), Integer.parseInt(cookie));

        return SectionMapper.INSTANSE.sectionToCreateSectionDtoResponse(section);
    }


    /**
     * 7.15. Переименование раздела.
     * PUT /api/sections/идентификатор_раздела
     *
     * @param cookie
     * @param dtoRequest
     * @param sectionId
     * @return
     * @throws ServerException
     */
    public RenameSectionDtoResponse rename(String cookie, RenameSectionDtoRequest dtoRequest, int sectionId) throws ServerException {
        LOGGER.debug("Action \"Rename section\" name: {}", dtoRequest.getName());
        Section section = sectionDao.getInfo(sectionId);
        if (section.getAuthor().getLogin() != userDao.getByToken(cookie).getLogin()) {
            if (userDao.getLoginIsSuperUser().contains(userDao.getByToken(cookie).getLogin())) {
                section.setName(dtoRequest.getName());
                return SectionMapper.INSTANSE.sectionToRenameSectionDtoResponse(sectionDao.rename(section));
            } else {
                throw new ServerException(ServerErrorCode.ACCESS_RIGHTS_ERROR);
            }
        } else {
            return SectionMapper.INSTANSE.sectionToRenameSectionDtoResponse(sectionDao.rename(section));
        }
    }

    /**
     *7.16. Удаление раздела
     *DELETE /api/sections/идентификатор_раздела
     *
     * @param cookie
     * @param sectionId
     * @return EmptyDtoResponse
     * @throws ServerException
     */
    public EmptyDtoResponse delete(String cookie, int sectionId) throws ServerException{
        LOGGER.debug("Action \"Delete section\" sectionId: {}",sectionId);
        Section section = sectionDao.getInfo(sectionId);
        if (section.getAuthor().getLogin() != userDao.getByToken(cookie).getLogin()) {
            if (userDao.getLoginIsSuperUser().contains(userDao.getByToken(cookie).getLogin())) {
                sectionDao.delete(sectionId);
            } else {
                throw new ServerException(ServerErrorCode.ACCESS_RIGHTS_ERROR);
            }
        } else {
            sectionDao.delete(sectionId);
        }
        return new EmptyDtoResponse();
    }


    /**
     * 7.17. Получение информации о разделе.
     * GET /api/sections/идентификатор_раздела
     *
     * @param cookie
     * @param sectionId
     * @return
     * @throws ServerException
     */
    public GetSectionInfoDtoResponse getInfo(String cookie, int sectionId) throws ServerException{
        LOGGER.debug("Action \"Delete section\" sectionId: {}",sectionId);
        return SectionMapper.INSTANSE.sectionToGetSectionInfoDtoResponse(sectionDao.getInfo(sectionId));
    }

    public GetSectionsDtoResponse getSections() throws ServerException {
        LOGGER.debug("Action \"Get sections\"");
        return SectionMapper.INSTANSE.sectionsToGetSectionsDtoResponse(sectionDao.getAll());

    }
}
