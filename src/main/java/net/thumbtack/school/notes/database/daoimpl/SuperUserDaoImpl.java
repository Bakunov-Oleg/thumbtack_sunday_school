package net.thumbtack.school.notes.database.daoimpl;

import net.thumbtack.school.notes.database.dao.SuperUserDao;
import net.thumbtack.school.notes.database.mappers.SuperUserMapper;
import net.thumbtack.school.notes.serverexception.ServerErrorCode;
import net.thumbtack.school.notes.serverexception.ServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SuperUserDaoImpl implements SuperUserDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(SuperUserDao.class);

    private SuperUserMapper superUserMapper;


    public SuperUserDaoImpl() {
    }

    @Autowired
    public SuperUserDaoImpl(SuperUserMapper superUserMapper) {
        this.superUserMapper = superUserMapper;
    }

    @Override
    @Transactional(rollbackFor = DataAccessException.class)
    public void insert(int userId) throws ServerException {
        LOGGER.debug("DAO insert SuperUser, userId: {}", userId);
        try {
            superUserMapper.insert(userId);
        } catch (DataAccessException ex) {
            LOGGER.info("Can't get insert SuperUser by userId: {}, {}", userId, ex);
            throw new ServerException(ServerErrorCode.ACCESS_RIGHTS_ERROR);
        }
    }
}
