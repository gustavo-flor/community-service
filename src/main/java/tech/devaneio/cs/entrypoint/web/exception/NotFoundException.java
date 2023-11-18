package tech.devaneio.cs.entrypoint.web.exception;

import org.springframework.web.server.ResponseStatusException;
import tech.devaneio.cs.core.exception.ResourceNotFoundException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public class NotFoundException extends ResponseStatusException {

    public NotFoundException(final ResourceNotFoundException cause) {
        super(NOT_FOUND, cause.getMessage(), cause);
    }

}
