package study.todolist.global.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import study.todolist.global.Envelope;
import study.todolist.global.error.exception.BusinessException;
import study.todolist.global.error.exception.EntityNotFoundException;
import study.todolist.global.error.exception.OverMaxCountException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * EntityNotFoundException 발생 메시지 처리
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public Envelope handleEntityNotFoundException(EntityNotFoundException e){
        log.error("EntityNotFoundException", e);

        return Envelope.fail(e.getErrorCode().getHttpStatus().value(), e.getMessage());
    }

    @ExceptionHandler(OverMaxCountException.class)
    public Envelope handleOverMaxCountException(OverMaxCountException e){
        log.error("OverMaxCountException", e);

        return Envelope.fail(e.getErrorCode().getHttpStatus().value(), e.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    protected Envelope handleException(BusinessException e){
        log.error("Exception", e);

        return Envelope.fail(e.getErrorCode().getHttpStatus().value(), e.getMessage());
    }
}
