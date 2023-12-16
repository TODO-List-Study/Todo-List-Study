package study.todolist.global.error.exception;

import study.todolist.global.error.ErrorCode;

public class EntityNotFoundException extends BusinessException{

    public EntityNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}

