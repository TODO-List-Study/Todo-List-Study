package study.todolist.domain.todo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import study.todolist.domain.todo.database.ConcurrentLRUCacheDB;
import study.todolist.domain.todo.dto.request.TodoRequest;
import study.todolist.domain.todo.dto.response.ViewSingleResponse;
import study.todolist.domain.todo.entity.Todo;
import study.todolist.domain.todo.entity.TodoTask;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {
    private ConcurrentLRUCacheDB<Long, Todo> cache;

    public TodoServiceImpl(@Value("${cache.maxSize}") int maxSize) {
        this.cache = new ConcurrentLRUCacheDB<>(maxSize);
    }

    public ViewSingleResponse createTodo(TodoRequest request) {
        TodoTask todoTask = TodoTask.from(request.getTask());
        Todo todo = Todo.of(todoTask, false, request.getPriority());
        cache.put(todo.getId(), todo);
        return new ViewSingleResponse(todo);
    }

    public List<ViewSingleResponse> getAllTodos() {
        return cache.values().stream()
                .filter(todo -> !todo.isDeleted())
                .sorted(Comparator.comparing(Todo::getPriority).reversed())
                .map(ViewSingleResponse::new)
                .collect(Collectors.toList());
    }

    public Optional<ViewSingleResponse> getSingleTodo(Long id) {
        return Optional.ofNullable(cache.get(id))
                .filter(todo -> !todo.isDeleted())
                .map(ViewSingleResponse::new);
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
