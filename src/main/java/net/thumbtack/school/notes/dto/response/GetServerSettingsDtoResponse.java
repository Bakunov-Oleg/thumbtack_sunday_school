package net.thumbtack.school.notes.dto.response;

public class GetServerSettingsDtoResponse {

    /*
    "maxNameLength": значение max_name_length,
    "minPasswordLength": значение min_password_length,
    "user_idle_timeout" : значение user_idle_timeout
     */

    private int maxNameLength;
    private int minPasswordLength;
    private int user_idle_timeout;

    public GetServerSettingsDtoResponse(int maxNameLength, int minPasswordLength, int user_idle_timeout) {
        this.maxNameLength = maxNameLength;
        this.minPasswordLength = minPasswordLength;
        this.user_idle_timeout = user_idle_timeout;
    }

    public int getMaxNameLength() {
        return maxNameLength;
    }

    public void setMaxNameLength(int maxNameLength) {
        this.maxNameLength = maxNameLength;
    }

    public int getMinPasswordLength() {
        return minPasswordLength;
    }

    public void setMinPasswordLength(int minPasswordLength) {
        this.minPasswordLength = minPasswordLength;
    }

    public int getUser_idle_timeout() {
        return user_idle_timeout;
    }

    public void setUser_idle_timeout(int user_idle_timeout) {
        this.user_idle_timeout = user_idle_timeout;
    }
}
