package study.todolist.domain.todo.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import study.todolist.domain.todo.entity.Todo;
import study.todolist.domain.todo.entity.Priority;

@Getter
@NoArgsConstructor
public class ViewSingleResponse {
    private Long id;
    private String task;
    private Priority priority;
    private Boolean deleted;

    public ViewSingleResponse(Todo todo) {
        this.id = todo.getId();
        this.task = todo.getTask().getTask();
        this.priority = todo.getPriority();
        this.deleted = todo.isDeleted();
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

    public boolean isDeleted() {
        return deleted;
    }
}
