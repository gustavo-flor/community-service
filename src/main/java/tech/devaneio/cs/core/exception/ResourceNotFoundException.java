package tech.devaneio.cs.core.exception;

public abstract class ResourceNotFoundException extends RuntimeException {

    protected ResourceNotFoundException(final String message) {
        super(message);
    }

}
