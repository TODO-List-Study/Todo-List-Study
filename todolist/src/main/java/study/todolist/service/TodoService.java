package study.todolist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.todolist.entity.todo.Status;
import study.todolist.entity.todo.TodoList;
import study.todolist.global.error.ErrorCode;
import study.todolist.global.error.exception.EntityNotFoundException;
import study.todolist.repository.todo.TodoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    // 단건조회
    public TodoList findById(Long id){

        return todoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUND_TODO));
    }
    // 전체조회
    public List<TodoList> findAll(){

        return todoRepository.findAll();
    }

    // 생성
    public TodoList createTodo(String title){

        TodoList todo = TodoList.builder()
                .title(title)
                .status(Status.BEFORE)
                .build();

        return todoRepository.save(todo);
    }

    // 수정 (수행 여부)
    public void updateStatus(Long id, Status status){

        TodoList findTodo = findById(id);

        findTodo.updateStatus(status);
    }

    // 수정 (내용)
    public Long updateTitle(Long id, String title){

        TodoList findTodo = findById(id);

        findTodo.updateTitle(title);

        return id;
    }

    public void delete(Long id){

        TodoList findTodo = findById(id);

        todoRepository.delete(findTodo);
    }
}
