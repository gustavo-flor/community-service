package tech.devaneio.cs.core.exception.handler;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.postgresql.util.PSQLException;
import org.postgresql.util.ServerErrorMessage;
import org.springframework.dao.DataIntegrityViolationException;
import tech.devaneio.cs.core.exception.ConflictException;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DataIntegrityViolationExceptionHandler {

    private static final String INTEGRITY_CONSTRAINT_VIOLATION_SQL_STATE_PREFIX = "23";

    public static RuntimeException handle(final DataIntegrityViolationException exception) {
        final var cause = exception.getMostSpecificCause();
        if (cause instanceof PSQLException sqlException) {
            final var sqlState = sqlException.getSQLState();
            if (sqlState.startsWith(INTEGRITY_CONSTRAINT_VIOLATION_SQL_STATE_PREFIX)) {
                final var detail = Optional.ofNullable(sqlException.getServerErrorMessage())
                    .map(ServerErrorMessage::getDetail)
                    .orElse("Unknown integrity constraint violation");
                return new ConflictException(detail, exception);
            }
        }
        return exception;
    }

}
