package study.todolist.domain.todo.exception;

public class IllegalArgumentException extends RuntimeException {
    public IllegalArgumentException(String msg) {
        super(msg);
    }
}
