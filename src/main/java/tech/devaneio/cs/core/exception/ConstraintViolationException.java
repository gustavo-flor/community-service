package tech.devaneio.cs.core.exception;

public class ConstraintViolationException extends RuntimeException {

    public ConstraintViolationException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
