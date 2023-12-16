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

    // 단건조회
    @GetMapping("/find/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id){

        TodoList todo = todoService.findById(id);
        Envelope response = Envelope.toEnvelope(todo);

        return ResponseEntity.ok(response);
    }

    // 전체조회
    @GetMapping("/find/all")
    public ResponseEntity findAll(){

        Envelope envelope = Envelope.toEnvelope(todoService.findAll());

        return ResponseEntity.ok(envelope);
    }

    // 수정
    @PatchMapping("/update/{id}")
    public ResponseEntity updateTodo(@PathVariable Long id,
                                     @RequestBody TodoDto.Request request){

        todoService.updateTitle(id, request.getTitle());

        return ResponseEntity.ok(Envelope.toEnvelope(todoService.findById(id)));
    }

    // check
    @PatchMapping("/check/{id}")
    public ResponseEntity checkTodo(@PathVariable Long id){

        todoService.updateCheck(id);

        return ResponseEntity.ok(Envelope.toEnvelope(todoService.findById(id)));
    }

    // 삭제
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTodo(@PathVariable Long id){

        todoService.delete(id);

        return ResponseEntity.ok(true);
    }
}

