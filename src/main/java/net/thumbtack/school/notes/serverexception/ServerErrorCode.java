package net.thumbtack.school.notes.serverexception;

public enum ServerErrorCode {
    INVALID_LOGIN_OR_PASSWORD("Неверный логин или пароль!"),
    INVALID_TOKEN("Неверный token!"),
    ENTRY_IS_ALREDY_IN_DATABASE("Данная запись уже есть в БД"),
    ENTRY_IS_NOT_FIND("Данная запись уже есть в БД"),
    USER_IS_NOT_SUPERUSER("Пользователь не является super user"),
    SECTION_NAME_IS_USED("Имя раздела уже используется"),
    SECTION_IS_NOT_FIND("Раздел не найден"),
    ACCESS_RIGHTS_ERROR("Ошибка прав доступа"),
    BAD_DATETIME("Неверная дата или время"),
    NAME_IS_EMPTY("Пустое имя"),
    NAME_LENGTH_IS_OVERSIZE("Слишком длинное имя"),
    NAME_IS_INVALID("Некорректное имя"),
    LOGIN_IS_EMPTY("Пустой логин"),
    LOGIN_LENGTH_IS_OVERSIZE("Слишком длинный логин"),
    LOGIN_IS_INVALID("Некорректный логин"),
    PASSWORD_IS_EMPTY("Пустой пароль"),
    PASSWORD_LENGHT_IS_SHORT("Короткий пароль"),
    PATRONYMIC_LENGTH_IS_OVERSIZE("Слишком длинное отчество"),
    PATRONYMIC_IS_INVALID("Некорректное отчество"),





    LOGIN_ALREADY_USED("Данный логин уже существует");

    private String errorString;

    private ServerErrorCode(String errorString) {
        this.errorString = errorString;
    }

    public String getErrorCode() {
        return errorString;
    }
}
