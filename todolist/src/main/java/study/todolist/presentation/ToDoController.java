package study.todolist.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import study.todolist.application.ToDoCommandService;
import study.todolist.application.ToDoQueryService;
import study.todolist.global.dto.ResponseEnvelope;
import study.todolist.presentation.dto.req.CreateToDoReq;
import study.todolist.presentation.dto.res.ToDoDetailRes;
import study.todolist.presentation.dto.res.ToDoIdRes;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class ToDoController {

    private final ToDoCommandService toDoCommandService;

    private final ToDoQueryService toDoQueryService;

    @PostMapping
    public ResponseEnvelope<ToDoIdRes> createToDo(@RequestBody CreateToDoReq createToDoReq) {
        Long toDoId = toDoCommandService.createToDo(createToDoReq.title(), createToDoReq.contents(), createToDoReq.category(), createToDoReq.postTime());

        return ResponseEnvelope.of(new ToDoIdRes(toDoId));
    }

    @GetMapping
    public ResponseEnvelope<ToDoDetailRes> findToDoById(@RequestParam("toDoId") Long todoId) {
        ToDoDetailRes toDoDetailRes = toDoQueryService.findToDoById(todoId);

        return ResponseEnvelope.of(toDoDetailRes);
    }
}
