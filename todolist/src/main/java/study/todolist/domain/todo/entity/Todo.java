package study.todolist.domain.todo.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.todolist.base.BaseEntity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Todo extends BaseEntity {
    private TodoTask task;
    private Boolean isCompleted;
    private Priority priority;

    public static Todo of(TodoTask task, Boolean isCompleted, Priority priority) {
        return new Todo(task, isCompleted, priority);
    }

    public void setTask(TodoTask task) {
        this.task = task;
        update();
    }

    @Override
    public boolean isDeleted() {
        return super.isDeleted();
    }
}
