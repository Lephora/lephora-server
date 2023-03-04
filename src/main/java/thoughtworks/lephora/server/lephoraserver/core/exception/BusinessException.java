package thoughtworks.lephora.server.lephoraserver.core.exception;

public class BusinessException extends RuntimeException {
    private final String errorCode;
    private final String errorMessage;

    public BusinessException(String errorCode, String message) {
        this.errorCode = errorCode;
        this.errorMessage = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
