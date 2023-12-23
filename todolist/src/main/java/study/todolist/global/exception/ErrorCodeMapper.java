package study.todolist.global.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import study.todolist.domain.todo.exception.TodoNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class ErrorCodeMapper {
    private static final Map<Class<? extends Exception>, ErrorCode> MAPPING = new HashMap<>();

    static {
        MAPPING.put(MethodArgumentNotValidException.class, ErrorCode.BAD_CREDENTIALS);
        MAPPING.put(ConstraintViolationException.class, ErrorCode.BAD_CREDENTIALS);
        MAPPING.put(IllegalArgumentException.class, ErrorCode.INVALID_INPUT_VALUE);
        MAPPING.put(TodoNotFoundException.class, ErrorCode.TODO_NOT_FOUND);
    }

    public static ErrorCode getErrorCode(Exception e) {
        return MAPPING.get(e.getClass());
    }
}
