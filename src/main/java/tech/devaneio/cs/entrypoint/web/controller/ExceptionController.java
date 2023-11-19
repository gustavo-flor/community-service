package tech.devaneio.cs.entrypoint.web.controller;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tech.devaneio.cs.core.exception.ConflictException;
import tech.devaneio.cs.core.exception.ResourceNotFoundException;
import tech.devaneio.cs.entrypoint.web.payload.response.ErrorPayload;

import java.util.stream.StreamSupport;

import static java.text.MessageFormat.format;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorPayload handle(final ConstraintViolationException exception) {
        final var violation = exception.getConstraintViolations()
            .stream()
            .findFirst()
            .orElseThrow();
        final var propertyPath = violation.getPropertyPath();
        final var propertyName = StreamSupport.stream(propertyPath.spliterator(), false)
            .reduce((first, second) -> second)
            .map(Path.Node::getName)
            .orElseThrow();
        final var message = format("{0}: {1}", propertyName, violation.getMessage());
        return ErrorPayload.invalidRequest(message);
    }

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorPayload handle(final ConflictException exception) {
        return ErrorPayload.conflictError(exception.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorPayload handle(final AccessDeniedException exception) {
        return ErrorPayload.forbidden(exception.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorPayload handle(final BadCredentialsException exception) {
        return ErrorPayload.unauthorized(exception.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorPayload handle(final ResourceNotFoundException exception) {
        return ErrorPayload.resourceNotFound(exception.getMessage());
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorPayload handle(final Exception exception) {
        log.error(exception.getMessage(), exception);
        return ErrorPayload.internalServerError();
    }

}
