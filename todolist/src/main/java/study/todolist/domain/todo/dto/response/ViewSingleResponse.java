package study.todolist.domain.todo.dto.response;

import study.todolist.domain.todo.entity.Todo;
import study.todolist.domain.todo.util.Priority;

public class ViewSingleResponse {
    private Long id;
    private String task;
    private Priority priority;

    public ViewSingleResponse(Todo todo) {
        this.id = todo.getId();
        this.task = todo.getTask().getTask();
        this.priority = todo.getPriority();
    }

    public Long getId() {
        return id;
    }

    public String getTask() {
        return task;
    }

    public Priority getPriority() {
        return priority;
    }
}
