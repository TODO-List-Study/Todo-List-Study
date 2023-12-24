package study.todolist.domain.todo.exception;

public class BulkCreateTodoLimitExceededException extends RuntimeException {
    public BulkCreateTodoLimitExceededException(String message) {
        super(message);
    }
}
