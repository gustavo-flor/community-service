package tech.devaneio.cs.entrypoint.web.payload.response;

public record ErrorPayload(String message, Code code) {

    public static ErrorPayload invalidRequest(final String message) {
        return new ErrorPayload(message, Code.INVALID_REQUEST);
    }

    public static ErrorPayload internalServerError() {
        final var message = "Something went wrong, please try again later. Check the logs for details.";
        return new ErrorPayload(message, Code.INTERNAL_SERVER_ERROR);
    }

    enum Code {

        INVALID_REQUEST,
        INTERNAL_SERVER_ERROR

    }

}
