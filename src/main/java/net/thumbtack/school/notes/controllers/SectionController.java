package net.thumbtack.school.notes.controllers;

import net.thumbtack.school.notes.dto.request.CreateSectionDtoRequest;
import net.thumbtack.school.notes.dto.request.RenameSectionDtoRequest;
import net.thumbtack.school.notes.dto.response.*;
import net.thumbtack.school.notes.serverexception.ServerException;
import net.thumbtack.school.notes.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sections")
@Validated
public class SectionController {
    private static final String COOKIE_NAME = "JAVASESSIONID";
    private final SectionService sectionService;

    @Autowired
    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    /*7.14. Создание раздела.
     *POST /api/sections */
    @PostMapping(path = "/sections", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CreateSectionDtoResponse create(
            @RequestBody CreateSectionDtoRequest dtoRequest,
            @CookieValue(COOKIE_NAME) String cookie
    ) throws ServerException {
        CreateSectionDtoResponse dtoResponse = sectionService.createSection(cookie, dtoRequest);
        return dtoResponse;
    }

    /*7.15. Переименование раздела.
     *PUT /api/sections/идентификатор_раздела */
    @PostMapping(path = "/sections/{sectionId}/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public RenameSectionDtoResponse rename(
            @PathVariable("sectionId") int sectionId,
            @CookieValue(COOKIE_NAME) String cookie,
            @RequestBody RenameSectionDtoRequest dtoRequest
    ) throws ServerException {
        RenameSectionDtoResponse dtoResponse = sectionService.rename(cookie, dtoRequest, sectionId);
        return dtoResponse;
    }

    /*7.16. Удаление раздела
    DELETE /api/sections/идентификатор_раздела
     */
    @DeleteMapping(path = "/sections/{sectionId}/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public EmptyDtoResponse delete(
            @PathVariable("sectionId") int sectionId,
            @CookieValue(COOKIE_NAME) String cookie
    ) throws ServerException {
        sectionService.delete(cookie, sectionId);
        return new EmptyDtoResponse();
    }

    /*
    7.17. Получение информации о разделе
    GET /api/sections/идентификатор_раздела
     */
    @GetMapping(path = "/sections/{sectionId}/")
    public GetSectionInfoDtoResponse getInfo(
            @PathVariable("sectionId") int sectionId,
            @CookieValue(COOKIE_NAME) String cookie
    ) throws ServerException {
        return sectionService.getInfo(cookie, sectionId);
    }

    /*
    7.18. Получение списка разделов
    GET /api/sections
     */
//    @GetMapping(path = "/sections/{sectionId}/")
//    public GetSectionsDtoResponse getSections(
//            @PathVariable("sectionId") int sectionId,
//            @CookieValue(COOKIE_NAME) String cookie
//    ) throws ServerException {
//        return sectionService.getSections();
//    }

}
