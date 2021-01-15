package net.thumbtack.school.notes;

// REVU это в model
public enum UserStatus {
    ACTIVE("Активен"),
    DELETED("Удален");

    private String userStatus;

    UserStatus(String typeTrack) {
        this.userStatus = typeTrack;
    }

    public String getTypeTrack() {
        return userStatus;
    }
}
