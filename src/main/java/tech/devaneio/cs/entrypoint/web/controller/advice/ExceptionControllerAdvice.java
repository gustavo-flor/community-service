package tech.devaneio.cs.entrypoint.web.controller.advice;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tech.devaneio.cs.entrypoint.web.exception.ConflictException;
import tech.devaneio.cs.entrypoint.web.exception.NotFoundException;
import tech.devaneio.cs.entrypoint.web.payload.response.ErrorPayload;

import java.util.stream.StreamSupport;

import static java.text.MessageFormat.format;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

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

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorPayload handle(final NotFoundException exception) {
        return ErrorPayload.resourceNotFound(exception.getMessage());
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorPayload handle(final Exception exception) {
        log.error(exception.getMessage(), exception);
        return ErrorPayload.internalServerError();
    }

}
