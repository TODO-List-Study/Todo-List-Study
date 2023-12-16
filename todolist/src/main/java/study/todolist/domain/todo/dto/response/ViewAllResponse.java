package study.todolist.domain.todo.dto.response;

import study.todolist.domain.todo.util.Priority;

public class ViewAllResponse {
    private Long id;
    private String task;
    private Boolean isCompleted;
    private Priority priority;
}
