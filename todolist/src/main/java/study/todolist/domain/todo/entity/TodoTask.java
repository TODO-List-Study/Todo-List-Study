package study.todolist.domain.todo.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TodoTask {

    private static final String INVALID_TITLE_LENGTH_MESSAGE = "공백을 제외하고 최소 1글자 이상이어야 합니다.";

    private String task;

    private TodoTask(String task) {
        this.task = task;
    }

    public static TodoTask from(String task) {
        task = Objects.requireNonNullElse(task, "");
        validate(task);

        return new TodoTask(task);
    }

    private static void validate(String task) {
        checkTitleEmpty(task);
    }

    private static void checkTitleEmpty(String task) {
        if (task.isBlank()) {
            throw new IllegalArgumentException(INVALID_TITLE_LENGTH_MESSAGE);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoTask todoTask = (TodoTask) o;
        return Objects.equals(task, todoTask.task);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(task);
    }

    @Override
    public String toString() {
        return task;
    }

    public String getTask() {
        return task;
    }
}