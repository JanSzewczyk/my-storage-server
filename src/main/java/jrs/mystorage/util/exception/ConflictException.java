package jrs.mystorage.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ConflictException extends ResponseStatusException {

    private static final HttpStatus status = HttpStatus.NOT_FOUND;

    public ConflictException() {
        super(status);
    }

    public ConflictException(String message) {
        super(status, message);
    }
}
