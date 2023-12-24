package study.todolist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import study.todolist.dto.TodoDto;
import study.todolist.global.Envelope;
import study.todolist.entity.todo.TodoList;
import study.todolist.service.TodoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todo")
public class TodoController {

    private final TodoService todoService;


    // 생성
    @PostMapping("/create")
    public Envelope createTodo(@RequestBody TodoDto.Request request){

        TodoList todo = todoService.createTodo(request.getTitle());

        TodoDto.Response response = TodoDto.Response.of(todo);

        return Envelope.success(response);
    }

    @PostMapping("/create/bulk/{id}")
    public Envelope<List> bulkCreate(@PathVariable("id") Long id,
                                     @RequestParam int count){

        List<TodoDto.Response> response = todoService.bulkTodo(id, count);

        return Envelope.success(response);
    }

    // 단건조회
    @GetMapping("/find/{id}")
    public Envelope findById(@PathVariable("id") Long id){

        TodoList todo = todoService.findById(id);

        TodoDto.Response response = TodoDto.Response.of(todo);

        return Envelope.success(response);
    }

    // 전체조회
    @GetMapping("/find/all")
    public Envelope findAll(){

        List<TodoDto.Response> responseList = todoService.findAll().stream()
                .map(TodoDto.Response::of)
                .collect(Collectors.toList());

        return Envelope.success(responseList);
    }

    // 수정
    @PatchMapping("/update/{id}")
    public Envelope updateTodo(@PathVariable Long id,
                                     @RequestBody TodoDto.Request request){

        Long findId = todoService.updateTitle(id, request.getTitle());
        TodoList todo = todoService.findById(findId);

        TodoDto.Response response = TodoDto.Response.of(todo);

        return Envelope.success(response);
    }

    // check
    @PatchMapping("/check/{id}")
    public Envelope checkTodo(@PathVariable Long id,
                              @RequestBody TodoDto.ChangeStatus status){

        todoService.updateStatus(id, status.getStatus());
        TodoDto.Response response = TodoDto.Response.of(todoService.findById(id));

        return Envelope.success(response);
    }

    // 삭제
    @DeleteMapping("/delete/{id}")
    public Envelope deleteTodo(@PathVariable Long id){

        todoService.delete(id);
        TodoDto.Response response = TodoDto.Response.of(todoService.findById(id));

        return Envelope.success(response);
    }
}

