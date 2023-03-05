package thoughtworks.lephora.server.lephoraserver.core.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public class RedisLockException extends RuntimeException {
    private final String errorCode = INTERNAL_SERVER_ERROR.name();
    private final String errorMessage;

    public RedisLockException(String message) {
        this.errorMessage = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
