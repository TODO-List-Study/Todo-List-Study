package study.todolist.domain.todo.service;

import study.todolist.domain.todo.dto.request.TodoRequest;
import study.todolist.domain.todo.dto.response.ViewSingleResponse;

import java.util.List;

public interface TodoService {
    ViewSingleResponse createTodo(TodoRequest request);
    List<ViewSingleResponse> getAllTodos();
    ViewSingleResponse getSingleTodo(Long id);
    ViewSingleResponse updateTodo(Long id, String taskStr);
    ViewSingleResponse deleteTodo(Long id);
    List<ViewSingleResponse> bulkCreateTodo(Integer count);
}
