package study.todolist.base.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import study.todolist.base.RsData;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final String INVALID_KEY_VALUE = "키 값이 유효하지 않습니다.";
    private static final String INVALID_INPUT_VALUE = "입력 값이 올바르지 않습니다.";

    @ExceptionHandler(IllegalArgumentException.class)
    public RsData<Void> handleIllegalArgumentException(IllegalArgumentException e) {
        String errorMessage = e.getMessage();
        if (errorMessage.equals("키는 Null이 아니여야 합니다.")) {
            return RsData.of("F-2", INVALID_KEY_VALUE);
        } else {
            return RsData.of("F-3", INVALID_INPUT_VALUE);
        }
    }
}
