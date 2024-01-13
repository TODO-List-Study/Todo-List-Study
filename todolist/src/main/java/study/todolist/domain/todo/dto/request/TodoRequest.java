package study.todolist.domain.todo.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import study.todolist.domain.todo.entity.Priority;

@Getter
@NoArgsConstructor
public class TodoRequest {
    private String task;
    private Priority priority;
    private Long memberId;

    public TodoRequest(String task, String priority, Long memberId) {
        this.task = task;
        this.priority = Priority.valueOf(priority.toUpperCase());
        this.memberId = getMemberId();
    }
}
