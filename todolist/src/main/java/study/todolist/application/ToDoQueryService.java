package study.todolist.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.todolist.domain.ToDo;
import study.todolist.domain.exception.NotFoundToDoException;
import study.todolist.domain.repository.ToDoRepository;
import study.todolist.presentation.dto.res.ToDoDetailRes;

@Service
@RequiredArgsConstructor
public class ToDoQueryService {

    private final ToDoRepository toDoRepository;


    public ToDoDetailRes findToDoById(Long id) {
        ToDo todo = toDoRepository.findById(id).orElseThrow(NotFoundToDoException::new);

        return new ToDoDetailRes(id,
                todo.getToDoEssential().getTitle(),
                todo.getToDoEssential().getContents(),
                todo.getToDoEssential().getCategory(),
                todo.getToDoEssential().getPostTime(),
                todo.getToDoEssential().getDayOfWeek(),
                todo.getViewer());
    }
}
