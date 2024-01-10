package study.todolist.global.error.exception;

import study.todolist.global.error.ErrorCode;

public class AuthenticationException extends BusinessException{

    public AuthenticationException(ErrorCode errorCode){
        super(errorCode);
    }
}
