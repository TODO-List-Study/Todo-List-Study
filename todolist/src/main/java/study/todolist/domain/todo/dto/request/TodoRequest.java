package study.todolist.domain.todo.dto.request;

import study.todolist.domain.todo.util.Priority;

public class TodoRequest {
    private String task;
    private Priority priority;

    public String getTask() {
        return task;
    }

    public Priority getPriority() {
        return priority;
    }
}
