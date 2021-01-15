package net.thumbtack.school.notes.controllers;


import net.thumbtack.school.notes.dto.response.EmptyDtoResponse;
import net.thumbtack.school.notes.dto.response.GetServerSettingsDtoResponse;
import net.thumbtack.school.notes.serverexception.ServerException;
import net.thumbtack.school.notes.service.DebugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/debug")
public class DebugController {
    private static final String COOKIE_NAME = "JAVASESSIONID";
    private DebugService debugService;

    public DebugController() {
    }

    @Autowired
    public DebugController(DebugService debugService) {
        this.debugService = debugService;
    }

    /*
    7.30. Получение настроек сервера
    GET /api/debug/settings
     */
//    @GetMapping(path = "/debug/settings")
//    public GetServerSettingsDtoResponse getSettings(
//    ) throws ServerException {
//        return debugService.getSettings();
//    }
//
//    /*
//    8.1. Очистка состояния сервера
//    POST /api/debug/clear
//     */
//    @PostMapping(path = "/clear")
//    public EmptyDtoResponse clear() throws ServerException {
//        return debugService.clear();
//    }
}
