package study.todolist.base.exceptionHandler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import study.todolist.base.RsData;
import study.todolist.domain.member.exception.MemberNotFoundException;
import study.todolist.domain.todo.exception.TodoNotFoundException;
import study.todolist.global.exception.ErrorCode;
import study.todolist.global.exception.ErrorCodeMapper;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public RsData<Void> handleIllegalArgumentException(IllegalArgumentException e) {
        ErrorCode errorCode = ErrorCodeMapper.getErrorCode(e);
        return RsData.of(errorCode);
    }

    @ExceptionHandler(TodoNotFoundException.class)
    public RsData<String> handleNotFoundException(TodoNotFoundException e) {
        ErrorCode errorCode = ErrorCodeMapper.getErrorCode(e);
        return RsData.of(errorCode);
    }

    @ExceptionHandler(MemberNotFoundException.class)
    public RsData<String> handleNotFoundException(MemberNotFoundException e) {
        ErrorCode errorCode = ErrorCodeMapper.getErrorCode(e);
        return RsData.of(errorCode);
    }
}
