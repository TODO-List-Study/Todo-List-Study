package study.todolist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.todolist.dto.TodoDto;
import study.todolist.global.Envelope;
import study.todolist.entity.TodoList;
import study.todolist.service.TodoService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todo")
public class TodoController {

    private final TodoService todoService;


    // 생성
    @PostMapping("/create")
    public ResponseEntity createTodo(@RequestBody TodoDto.Request request){

        Long id = todoService.createTodo(request.getTitle());

        return ResponseEntity.ok(Envelope.toEnvelope(todoService.findById(id)));
    }
}

