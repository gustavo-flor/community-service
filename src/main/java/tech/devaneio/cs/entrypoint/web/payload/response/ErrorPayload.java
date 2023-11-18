package tech.devaneio.cs.entrypoint.web.payload.response;

public record ErrorPayload(String message, Code code) {

    public static ErrorPayload invalidRequest(final String message) {
        return new ErrorPayload(message, Code.INVALID_REQUEST);
    }

    public static ErrorPayload conflictError(final String message) {
        return new ErrorPayload(message, Code.CONFLICT_ERROR);
    }

    public static ErrorPayload resourceNotFound(final String message) {
        return new ErrorPayload(message, Code.RESOURCE_NOT_FOUND);
    }

    public static ErrorPayload forbidden(final String message) {
        return new ErrorPayload(message, Code.FORBIDDEN);
    }

    public static ErrorPayload unauthorized(final String message) {
        return new ErrorPayload(message, Code.UNAUTHORIZED);
    }

    public static ErrorPayload internalServerError() {
        final var message = "Something went wrong, please try again later. Check the logs for details.";
        return new ErrorPayload(message, Code.INTERNAL_SERVER_ERROR);
    }

    enum Code {

        INVALID_REQUEST,
        CONFLICT_ERROR,
        RESOURCE_NOT_FOUND,
        INTERNAL_SERVER_ERROR,
        FORBIDDEN,
        UNAUTHORIZED

    }

}
