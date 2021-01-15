package net.thumbtack.school.notes.controllers;

import net.thumbtack.school.notes.dto.request.CreateNoteDtoRequest;
import net.thumbtack.school.notes.dto.request.EditNoteDtoRequest;
import net.thumbtack.school.notes.dto.request.EvaluateRatingNoteDtoRequest;
import net.thumbtack.school.notes.dto.response.*;
import net.thumbtack.school.notes.serverexception.ServerException;
import net.thumbtack.school.notes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/notes")
@Validated
public class NoteController {
    private static final String COOKIE_NAME = "JAVASESSIONID";
    private final NoteService noteService;

    @Autowired

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    /*7.19. Создание заметки.
     *POST /api/notes */
    @PostMapping(path = "/notes", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CreateNoteDtoResponse create(
            @RequestBody CreateNoteDtoRequest dtoRequest,
            @CookieValue(COOKIE_NAME) String cookie,
            HttpServletResponse response
    ) throws ServerException {
        CreateNoteDtoResponse dtoResponse = noteService.create(cookie, dtoRequest);
        return dtoResponse;
    }

    /*7.20. Получение информации о заметке.
    GET /api/notes/идентификатор_заметки
     */
    @GetMapping(path = "/notes/{noteId}/")
    public GetNoteInfoDtoResponse getInfo(
            @PathVariable("noteId") int noteId,
            @CookieValue(COOKIE_NAME) String cookie
    ) throws ServerException {
        return noteService.getInfo(noteId);
    }

    /*
    7.21. Редактирование и/или перенос заметки.
    PUT /api/notes/идентификатор_заметки
    */
    @PutMapping(path = "/notes/{noteId}/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public EditNoteDtoResponse editOrReposition(
            @PathVariable("noteId") int noteId,
            @CookieValue(COOKIE_NAME) String cookie,
            @RequestBody EditNoteDtoRequest dtoRequest
    ) throws ServerException {
        return noteService.editOrReposition(cookie, dtoRequest, noteId);
    }

    /*
    7.22. Удаление заметки.
    DELETE /api/notes/идентификатор_заметки
     */
    @DeleteMapping(path = "/notes/{noteId}/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public EmptyDtoResponse delete(
            @PathVariable("noteId") int noteId,
            @CookieValue(COOKIE_NAME) String cookie
    ) throws ServerException {
        noteService.delete(cookie, noteId);
        return new EmptyDtoResponse();
    }

    /*
    7.28. Оценка заметки.
    POST /api/notes/идентификатор_заметки/rating
    */
    @PostMapping(path = "/notes/{noteId}/rating", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmptyDtoResponse evaluateRating(
            @PathVariable("noteId") int noteId,
            @RequestBody EvaluateRatingNoteDtoRequest dtoRequest,
            @CookieValue(COOKIE_NAME) String cookie,
            HttpServletResponse response
    ) throws ServerException {
        noteService.evaluateRating(noteId, dtoRequest.getRating());
        return new EmptyDtoResponse();
    }

    /*
    7.29. Получение заметок.
    GET /api/notes
     */
    @GetMapping(path = "/accounts")
    public GetAllNotesDtoResponse getAccounts(
            @CookieValue(COOKIE_NAME) String cookie,
            @RequestParam(name = "sectionId ", defaultValue = "0") int sectionId,
            @RequestParam(name = "sortByRating", defaultValue = "no") String sortByRating, // asc | desc
            @RequestParam(name = "tags", defaultValue = "NULL") String tags, //= [тег,..”тег] tags - список ключевых слов, которые встречаются в заметке.
            @RequestParam(name = "alltags", defaultValue = "false") Boolean alltags, // = true Если параметр alltags присутствует, то выдаются заметки, содержащие все слова из этого набора, в противном случае выдаются заметки, содержащие хотя бы одно слово из этого набора
            @RequestParam(name = "timeFrom", defaultValue = "0") LocalDateTime timeFrom,
            @RequestParam(name = "timeTo", defaultValue = "0") LocalDateTime timeTo,
            @RequestParam(name = "user", defaultValue = "0") int user, //
            @RequestParam(name = "include", defaultValue = "NULL") String include, //указывает, какие заметки должны быть выданы. Если указан параметр “user”, параметр include игнорируется
            @RequestParam(name = "comments", defaultValue = "0") Boolean comments, //вместе с заметками выдаются все примечания к ним
            @RequestParam(name = "allVersions", defaultValue = "false") Boolean allVersions, //выдаются все версии заметок
            @RequestParam(name = "commentVersion", defaultValue = "false") Boolean commentVersion, //для каждого примечания выдается номер версии заметки, к которой было сделано это примечание
            @RequestParam(name = "from", defaultValue = "0") int from,
            @RequestParam(name = "count", defaultValue = "0") int count

    ) throws ServerException {
        return noteService.getAll(cookie,
                sectionId,
                sortByRating,
                tags,
                alltags,
                timeFrom,
                timeTo,
                user,
                include,
                comments,
                allVersions,
                commentVersion,
                from,
                count);
    }


}
