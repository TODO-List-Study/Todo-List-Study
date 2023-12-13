package study.todolist.domain.todo.service;

import study.todolist.domain.todo.dto.request.TodoRequest;
import study.todolist.domain.todo.dto.response.ViewSingleResponse;

import java.util.Optional;

public interface TodoService {
    ViewSingleResponse createTodo(TodoRequest request);
    Optional<ViewSingleResponse> getTodo(Long id);
    Optional<ViewSingleResponse> updateTodo(Long id, String taskStr);
    Optional<ViewSingleResponse> deleteTodo(Long id);
}
