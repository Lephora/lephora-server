package thoughtworks.lephora.server.lephoraserver.core.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.internalServerError;

@ControllerAdvice
public class ExceptionHandlerController {

    private static final String UNKNOWN_ERROR_CODE = "000000";

    private final MessageSource messageSource;

    public ExceptionHandlerController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ExceptionResponseBody> domainExceptionHandler(BusinessException exception, WebRequest request) {
        var errorMessage = messageSource.getMessage(exception.getErrorCode(), new Object[]{}, request.getLocale());
        return badRequest().body(new ExceptionResponseBody(
                exception.getErrorCode(),
                "%s: %s".formatted(errorMessage, exception.getErrorMessage())));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ExceptionResponseBody> domainExceptionHandler(ConstraintViolationException exception, WebRequest request) {
        final var constraintViolations = exception.getConstraintViolations();
        var iterator = constraintViolations.iterator();
        String errorCode = null;
        if (iterator.hasNext()) {
            errorCode = iterator.next().getMessage();
        }
        errorCode = errorCode == null || errorCode.isEmpty() ? UNKNOWN_ERROR_CODE : errorCode;
        var errorMessage = messageSource.getMessage(errorCode, new Object[]{}, request.getLocale());
        return badRequest().body(new ExceptionResponseBody(
                errorCode,
                errorMessage));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ExceptionResponseBody> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception, WebRequest request) {
        var locale = request.getLocale();
        var fieldError = exception.getBindingResult().getFieldError();
        if (fieldError == null) return badRequest().body(new ExceptionResponseBody(UNKNOWN_ERROR_CODE, null));
        var errorCode = fieldError.getDefaultMessage();
        errorCode = errorCode == null || errorCode.isEmpty() ? UNKNOWN_ERROR_CODE : errorCode;
        var errorMessage = messageSource.getMessage(errorCode, new Object[]{fieldError.getRejectedValue()}, locale);
        var errorResponseBody = new ExceptionResponseBody(errorCode, errorMessage);
        return badRequest().body(errorResponseBody);
    }

    @ExceptionHandler(RedisLockException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ResponseEntity<ExceptionResponseBody> redisLockExceptionHandler(RedisLockException exception) {
        return internalServerError()
                .body(new ExceptionResponseBody(exception.getErrorCode(), "%s: %s".formatted(exception.getErrorMessage(), exception.getMessage())));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ResponseEntity<ExceptionResponseBody> serverInternalExceptionHandler(Exception exception) {
        exception.printStackTrace();
        return internalServerError()
                .body(new ExceptionResponseBody(INTERNAL_SERVER_ERROR.name(), exception.getMessage()));
    }
}
