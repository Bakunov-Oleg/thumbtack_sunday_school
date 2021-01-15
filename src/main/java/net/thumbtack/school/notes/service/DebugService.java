package net.thumbtack.school.notes.service;

import net.thumbtack.school.notes.database.dao.DebugDao;
import net.thumbtack.school.notes.dto.response.EmptyDtoResponse;
import net.thumbtack.school.notes.dto.response.GetServerSettingsDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = DataAccessException.class)
public class DebugService {
    private final DebugDao debugDao;

    @Autowired
    public DebugService(DebugDao debugDao) {
        this.debugDao = debugDao;
    }

//    public GetServerSettingsDtoResponse getSettings() {
//
//    }
//
//    public EmptyDtoResponse clear() {
//    }
}
