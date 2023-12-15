package study.todolist.domain.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.todolist.domain.todo.database.InMemoryDB;
import study.todolist.domain.todo.dto.request.TodoRequest;
import study.todolist.domain.todo.dto.response.ViewSingleResponse;
import study.todolist.domain.todo.entity.Todo;
import study.todolist.domain.todo.entity.TodoTask;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final InMemoryDB<Long, Todo> inMemoryDB;

    @Override
    public ViewSingleResponse createTodo(TodoRequest request) {
        TodoTask todoTask = TodoTask.from(request.getTask());
        Todo todo = Todo.of(todoTask, false, request.getPriority());
        inMemoryDB.save(todo.getId(), todo);
        return new ViewSingleResponse(todo);
    }

    @Override
    public List<ViewSingleResponse> getAllTodos() {
        return inMemoryDB.findAll().stream()
                .filter(todo -> !todo.isDeleted())
                .sorted(Comparator.comparing(Todo::getPriority).reversed())
                .map(ViewSingleResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ViewSingleResponse> getSingleTodo(Long id) {
        return Optional.ofNullable(inMemoryDB.findById(id))
                .filter(todo -> !todo.isDeleted())
                .map(ViewSingleResponse::new);
    }

    @Override
    public Optional<ViewSingleResponse> updateTodo(Long id, String taskStr) {
        TodoTask task = TodoTask.from(taskStr);
        Optional<Todo> todoOptional = Optional.ofNullable(inMemoryDB.findById(id)).filter(todo -> !todo.isDeleted());
        todoOptional.ifPresent(todo -> {
            todo.setTask(task);
            inMemoryDB.save(id, todo);
        });
        return todoOptional.map(ViewSingleResponse::new);
    }

    @Override
    public Optional<ViewSingleResponse> deleteTodo(Long id) {
        Todo todo = inMemoryDB.findById(id);
        if (todo != null && !todo.isDeleted()) {
            todo.delete();
            return Optional.of(new ViewSingleResponse(todo));
        }
        return Optional.empty();
    }

}
