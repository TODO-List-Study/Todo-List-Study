package study.todolist.domain.todo.controller;

import org.springframework.web.bind.annotation.*;
import study.todolist.base.RsData;
import study.todolist.domain.todo.dto.request.TodoRequest;
import study.todolist.domain.todo.dto.response.ViewSingleResponse;
import study.todolist.domain.todo.service.TodoServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private final TodoServiceImpl todoService;

    public TodoController(TodoServiceImpl todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public RsData<ViewSingleResponse> createTodo(@RequestBody TodoRequest request) {
        ViewSingleResponse response = todoService.createTodo(request);
        return RsData.successOf(response);
    }

    @PatchMapping("/{id}")
    public RsData<ViewSingleResponse> updateTodo(@PathVariable Long id,
                                                 @RequestBody TodoRequest request) {
        Optional<ViewSingleResponse> response = todoService.updateTodo(id, request.getTask());
        return response.map(RsData::successOf).orElseGet(() -> RsData.failOf(null));
    }

    @DeleteMapping("/{id}")
    public RsData<ViewSingleResponse> deleteTodo(@PathVariable Long id) {
        Optional<ViewSingleResponse> response = todoService.deleteTodo(id);
        return response.map(RsData::successOf).orElseGet(() -> RsData.failOf(null));
    }

    @GetMapping
    public RsData<List<ViewSingleResponse>> getAllTodos() {
        List<ViewSingleResponse> responses = todoService.getAllTodos();
        return RsData.successOf(responses);
    }

    @GetMapping("/{id}")
    public RsData<ViewSingleResponse> getSingleTodo(@PathVariable Long id) {
        Optional<ViewSingleResponse> response = todoService.getSingleTodo(id);
        return response.map(RsData::successOf).orElseGet(() -> RsData.failOf(null));
    }
}
