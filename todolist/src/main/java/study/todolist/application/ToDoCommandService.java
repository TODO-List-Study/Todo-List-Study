package study.todolist.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.todolist.domain.Category;
import study.todolist.domain.Member;
import study.todolist.domain.ToDo;
import study.todolist.domain.repository.ToDoRepository;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class ToDoCommandService {

    private final ToDoRepository toDoRepository;
    public static final Long DEFAULT_ID = 1L;

    public Long createToDo(String title, String contents, Category category, ZonedDateTime postTime) {
        Long todoId = toDoRepository.save(DEFAULT_ID, new ToDo(title, contents, category, postTime, 0, new Member()));

        return todoId;
    }
}
