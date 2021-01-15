package net.thumbtack.school.notes.database;

import net.thumbtack.school.notes.UserStatus;
import net.thumbtack.school.notes.database.dao.*;
import net.thumbtack.school.notes.database.daoimpl.*;
import net.thumbtack.school.notes.database.model.Note;
import net.thumbtack.school.notes.database.model.Section;
import net.thumbtack.school.notes.database.model.User;
import net.thumbtack.school.notes.serverexception.ServerException;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Commit;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({SessionDaoImpl.class, UserDaoImpl.class, DebugDaoImpl.class, SuperUserDaoImpl.class, NoteDaoImpl.class, SectionDaoImpl.class})
@Commit
public class NoteDaoTest extends DatabasePrepare{
    private final UserDao userDao;
    private final NoteDao noteDao;
    private final SectionDao sectionDao;

    @Autowired
    public NoteDaoTest(SessionDao sessionDao, UserDao userDao, DebugDao debugDao, SuperUserDao superUserDao, NoteDao noteDao, SectionDao sectionDao) {
        super(userDao, debugDao, superUserDao);
        this.userDao = userDao;
        this.noteDao = noteDao;
        this.sectionDao = sectionDao;
    }

    @Test
    public void testInsert() throws ServerException {
        User user = new User(0,
                "User1",
                "User1",
                "User1",
                "Kolas",
                "User1",
                0.0,
                UserStatus.ACTIVE,
                LocalDateTime.now(),
                null);
        userDao.insert(user);
        User finalUser = userDao.getByLogin(user.getLogin(), user.getPassword() );
        Section section = sectionDao.insert("Name", finalUser.getId());

        Note note = noteDao.insert(finalUser.getId(), "subject", "body", section.getId());
        assertAll(
                () -> assertEquals(note.getAuthor().getId(), finalUser.getId()),
                () -> assertEquals(note.getSubject(), "subject"),
                () -> assertEquals(note.getAuthor().getLogin(), finalUser.getLogin())
        );
    }

    @Test
    public void testUpdateOrRepositionById() throws ServerException {
//        User user = new User(0,
//                "User1",
//                "User1",
//                "User1",
//                "Kolas",
//                "User1",
//                0.0,
//                UserStatus.ACTIVE,
//                LocalDateTime.now(),
//                null);
//        userDao.insert(user);
//        User finalUser = userDao.getByLogin(user.getLogin(), user.getPassword() );
//        Section section = sectionDao.insert("Name", finalUser.getId());
//        Section section1 = sectionDao.insert("Name2", finalUser.getId());
//
//        Note note = noteDao.insert(finalUser.getId(), "subject", "body", section.getId());
//        noteDao.updateOrRepositionById(note.getId(), "newBody", section1.getId());
//
//        assertAll(
//                () -> assertEquals(note.getAuthor().getId(), finalUser.getId()),
//                () -> assertEquals(note.getSection().getId(), section1.getId()),
//                () -> assertEquals(note.getAuthor().getLogin(), finalUser.getLogin())
//        );
    }

    @Test
    public void testDeleteById() throws ServerException {

    }

    @Test
    public void testDeleteAllByAuthorId() throws ServerException {

    }

    @Test
    public void testGetById() throws ServerException {

    }

    @Test
    public void testGetByAllWordsInSubject() throws ServerException {

    }

    @Test
    public void testGetByOneWordInSubject() throws ServerException {

    }

    @Test
    public void testGetByMaxRating() throws ServerException {

    }

    @Test
    public void testGetByMinRating() throws ServerException {

    }

    @Test
    public void testEvaluateRating() throws ServerException {

    }

    @Test
    public void testGetAllNotesByAuthorId() throws ServerException{

    }
    @Test
    public void testGetAllNotesFollowers() throws ServerException{

    }
    @Test
    public void testGetAllNotesIgnoredBy() throws ServerException {}

    @Test
    public void testGetAllRevisionNote() throws ServerException {

    }
    @Test
    public void testGetAllNotes() throws ServerException {

    }

}
