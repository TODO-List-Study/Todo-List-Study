package study.todolist.domain.todo.exception;

public class TodoCreationLimitExceededException extends RuntimeException {
    public TodoCreationLimitExceededException (String message) {
        super(message);
    }
}
