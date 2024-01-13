package study.todolist.base.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import study.todolist.base.RsData;
import study.todolist.domain.member.exception.MemberNotFoundException;
import study.todolist.domain.member.exception.NotMatchPasswordException;
import study.todolist.domain.todo.exception.TodoNotFoundException;
import study.todolist.global.exception.ErrorCode;
import study.todolist.global.exception.ErrorCodeMapper;

import javax.security.auth.login.AccountNotFoundException;

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

    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public RsData handleAccountNotFound(AccountNotFoundException e) {
        return RsData.failOf(e.getMessage());
    }

    @ExceptionHandler(NotMatchPasswordException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public RsData handleNotMatchPassword(NotMatchPasswordException e) {
        return RsData.failOf(e.getMessage());
    }

}
