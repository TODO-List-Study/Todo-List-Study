package study.todolist.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.todolist.application.ToDoCommandService;
import study.todolist.global.dto.ResponseEnvelope;
import study.todolist.presentation.dto.req.CreateToDoReq;
import study.todolist.presentation.dto.res.ToDoIdRes;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class ToDoController {

    private final ToDoCommandService toDoCommandService;

    @PostMapping
    public ResponseEnvelope<ToDoIdRes> createToDo(@RequestBody CreateToDoReq createToDoReq) {
        Long toDoId = toDoCommandService.createToDo(createToDoReq.title(), createToDoReq.contents(), createToDoReq.category(), createToDoReq.postTime());

        return ResponseEnvelope.of(new ToDoIdRes(toDoId));
    }
}
