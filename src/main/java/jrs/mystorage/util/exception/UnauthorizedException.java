package jrs.mystorage.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UnauthorizedException extends ResponseStatusException {

    private static final HttpStatus status = HttpStatus.UNAUTHORIZED;

    public UnauthorizedException() {
        super(status);
    }

    public UnauthorizedException(String message) {
        super(status, message);
    }
}
