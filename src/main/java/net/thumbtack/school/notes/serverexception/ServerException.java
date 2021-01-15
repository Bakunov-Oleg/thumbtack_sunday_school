package net.thumbtack.school.notes.serverexception;

import java.util.Objects;

public class ServerException extends Exception{
    private ServerErrorCode exceptionCode;

    public ServerException(ServerErrorCode exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public ServerErrorCode getErrorCode() {
        return this.exceptionCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServerException that = (ServerException) o;
        return Objects.equals(exceptionCode, that.exceptionCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exceptionCode);
    }
}
