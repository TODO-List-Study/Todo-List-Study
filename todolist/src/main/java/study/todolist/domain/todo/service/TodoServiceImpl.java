package study.todolist.domain.todo.service;

import study.todolist.domain.todo.database.ConcurrentLRUCacheDB;
import study.todolist.domain.todo.dto.request.TodoRequest;
import study.todolist.domain.todo.dto.response.ViewSingleResponse;
import study.todolist.domain.todo.entity.Todo;
import study.todolist.domain.todo.entity.TodoTask;

import java.util.Optional;

public class TodoServiceImpl implements TodoService {
    private ConcurrentLRUCacheDB<Long, Todo> cache;

    public TodoServiceImpl(final int maxSize) {
        this.cache = new ConcurrentLRUCacheDB<>(maxSize);
    }

    public ViewSingleResponse createTodo(TodoRequest request) {
        TodoTask todoTask = TodoTask.from(request.getTask());
        Todo todo = Todo.of(todoTask, false, request.getPriority());
        cache.put(todo.getId(), todo);
        return new ViewSingleResponse(todo);
    }

    public Optional<ViewSingleResponse> getTodo(Long id) {
        return Optional.ofNullable(cache.get(id)).filter(todo -> !todo.isDeleted()).map(ViewSingleResponse::new);
    }

    public Optional<ViewSingleResponse> updateTodo(Long id, String taskStr) {
        TodoTask task = TodoTask.from(taskStr);
        Optional<Todo> todoOptional = Optional.ofNullable(cache.get(id)).filter(todo -> !todo.isDeleted());
        todoOptional.ifPresent(todo -> todo.setTask(task));
        return todoOptional.map(ViewSingleResponse::new);
    }

    public Optional<ViewSingleResponse> deleteTodo(Long id) {
        Todo todo = cache.get(id);
        if (todo != null && !todo.isDeleted()) {
            todo.delete();
            return Optional.of(new ViewSingleResponse(todo));
        }
        return Optional.empty();
    }
}
