package tech.devaneio.cs.core.exception;

public class ConflictException extends RuntimeException {

    public ConflictException(final String message) {
        super(message);
    }

    public ConflictException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
