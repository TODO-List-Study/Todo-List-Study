package study.todolist.domain.todo.entity;

import study.todolist.base.BaseEntity;
import study.todolist.domain.todo.util.Priority;

public class Todo extends BaseEntity {
    private TodoTask task;
    private Boolean isCompleted;
    private Priority priority;

    private Todo(TodoTask task, Boolean isCompleted, Priority priority) {
        this.task = task;
        this.isCompleted = isCompleted;
        this.priority = priority;
    }

    public static Todo of(TodoTask task, Boolean isCompleted, Priority priority) {
        return new Todo(task, isCompleted, priority);
    }

    public TodoTask getTask() {
        return task;
    }

    public void setTask(TodoTask task) {
        this.task = task;
        update();
    }

    public Priority getPriority() {
        return priority;
    }
}
