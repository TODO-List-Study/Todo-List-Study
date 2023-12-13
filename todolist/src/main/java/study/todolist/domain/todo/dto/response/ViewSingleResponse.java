package study.todolist.domain.todo.dto.response;

public class ViewSingleResponse {
    private Long id;
    private String task;

    public ViewSingleResponse(Long id, String task) {
        this.id = id;
        this.task = task;
    }
}
