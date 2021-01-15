// REVU почему net.thumbtack.school.notes.database.model ?
// просто net.thumbtack.school.notes.model
// модель - она определяется задачей, а будет тут хранение в БД или нет - это не имеет к задаче отношения
// может, у нас вообще не будет БД, а вместо этого мы в DAO будем делать запросы
// к какому-то внешнему ресурсу
package net.thumbtack.school.notes.database.model;

import java.time.LocalDateTime;

public class Comment {
    private int id;
    private String body;
    private NoteRevision revision;
    private User author;
    private LocalDateTime created;
    private Double rating;

}
