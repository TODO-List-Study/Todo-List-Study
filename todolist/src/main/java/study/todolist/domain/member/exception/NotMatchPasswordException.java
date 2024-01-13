package study.todolist.domain.member.exception;

public class NotMatchPasswordException extends RuntimeException {
    public NotMatchPasswordException() {

    }

    public NotMatchPasswordException(String msg) {
        super(msg);
    }

    public NotMatchPasswordException(String message, Throwable cause, boolean enableSuppression,
                                     boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
