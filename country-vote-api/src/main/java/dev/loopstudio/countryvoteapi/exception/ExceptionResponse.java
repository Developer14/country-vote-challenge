package dev.loopstudio.countryvoteapi.exception;

import java.time.Instant;

/**
 * The type Exception response.
 * @author Victor Morales
 */
public record ExceptionResponse(
        Instant timestamp,
        String title,
        String status,
        String errorCode,
        String details,
        String[] fieldsErrors
) {
}
