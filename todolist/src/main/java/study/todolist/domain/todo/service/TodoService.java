package study.todolist.domain.todo.service;

import study.todolist.domain.todo.dto.request.TodoRequest;
import study.todolist.domain.todo.dto.response.ViewSingleResponse;

import java.util.List;
import java.util.Optional;

public interface TodoService {
    ViewSingleResponse createTodo(TodoRequest request);
    List<ViewSingleResponse> getAllTodos();
    Optional<ViewSingleResponse> getSingleTodo(Long id);
    Optional<ViewSingleResponse> updateTodo(Long id, String taskStr);
    Optional<ViewSingleResponse> deleteTodo(Long id);
}
