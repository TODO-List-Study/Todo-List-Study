package study.todolist.global.error.exception;

import study.todolist.global.error.ErrorCode;

public class OverMaxCountException extends BusinessException{

    public OverMaxCountException(ErrorCode errorCode) {
        super(errorCode);
    }
}
