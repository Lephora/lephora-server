package thoughtworks.lephora.server.lephoraserver.core.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.ResponseEntity.badRequest;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ExceptionResponseBody> domainExceptionHandler(BusinessException exception, WebRequest request) {
        return badRequest().body(new ExceptionResponseBody(
                exception.getErrorCode(),
                "%s: %s".formatted(exception.getErrorMessage(), request.getDescription(false))));
    }
}
