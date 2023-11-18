package tech.devaneio.cs.entrypoint.web.exception;

import tech.devaneio.cs.core.exception.ConstraintViolationException;

public class ConflictException extends RuntimeException {

    public ConflictException(final ConstraintViolationException cause) {
        super(cause.getMessage(), cause);
    }

}
