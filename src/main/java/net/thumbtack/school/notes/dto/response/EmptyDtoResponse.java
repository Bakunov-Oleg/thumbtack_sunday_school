package net.thumbtack.school.notes.dto.response;

import org.apache.htrace.shaded.fasterxml.jackson.annotation.JsonValue;

public class EmptyDtoResponse {
    private final String emptyResponse = "{}";

    @JsonValue
    public String getEmptyResponse() {
        return emptyResponse;
    }
}
