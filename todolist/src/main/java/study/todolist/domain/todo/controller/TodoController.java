package study.todolist.domain.todo.controller;

import org.springframework.web.bind.annotation.*;
import study.todolist.base.RsData;
import study.todolist.domain.todo.dto.request.TodoRequest;
import study.todolist.domain.todo.dto.response.ViewSingleResponse;
import study.todolist.domain.todo.exception.NotFoundException;
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
    public RsData<ViewSingleResponse> updateTodo(@PathVariable("id") Long id,
                                                 @RequestBody TodoRequest request) {
        try {
            ViewSingleResponse response = todoService.updateTodo(id, request.getTask());
            return RsData.successOf(response);
        } catch (NotFoundException e) {
            return RsData.failOf(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public RsData<ViewSingleResponse> deleteTodo(@PathVariable("id") Long id) {
        try {
            ViewSingleResponse response = todoService.deleteTodo(id);
            return RsData.successOf(response);
        } catch (NotFoundException e) {
            return RsData.failOf(e.getMessage());
        }
    }

    @GetMapping
    public RsData<List<ViewSingleResponse>> getAllTodos() {
        List<ViewSingleResponse> responses = todoService.getAllTodos();
        return RsData.successOf(responses);
    }

    @GetMapping("/{id}")
    public RsData<ViewSingleResponse> getSingleTodo(@PathVariable("id") Long id) {
        try {
            ViewSingleResponse response = todoService.getSingleTodo(id);
            return RsData.successOf(response);
        } catch (NotFoundException e) {
            return RsData.failOf(e.getMessage());
        }
    }

}
