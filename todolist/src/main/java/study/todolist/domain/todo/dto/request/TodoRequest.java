package study.todolist.domain.todo.dto.request;

import study.todolist.domain.todo.util.Priority;

public class TodoRequest {
    private String task;
    private Priority priority;

    public TodoRequest(String task, String priority) {
        this.task = task;
        this.priority = Priority.valueOf(priority.toUpperCase());
    }

    public String getTask() {
        return task;
    }

    public Priority getPriority() {
        return priority;
    }
}
