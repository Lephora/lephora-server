package thoughtworks.lephora.server.lephoraserver.core.exception;

public record ExceptionResponseBody(
        String errorCode,
        String errorMessage
) {
}
