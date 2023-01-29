package org.example.exception;

import java.time.Instant;
import java.util.List;

public record ErrorResponse(

        String message,
        Instant timestamp,
        List<Error> errors) {


    public ErrorResponse(String message, List<Error> errorList) {
        this(message, Instant.now(), errorList);
    }

    public ErrorResponse(String message) {
        this(message, Instant.now(), null);
    }

    public record Error(
            int code,
            String message) {
    }
}
