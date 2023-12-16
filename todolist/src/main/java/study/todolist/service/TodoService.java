package study.todolist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.todolist.entity.TodoList;
import study.todolist.global.error.ErrorCode;
import study.todolist.global.error.exception.EntityNotFoundException;
import study.todolist.repository.TodoRepository;

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
    public Long createTodo(String title){

        TodoList todo = TodoList.builder()
                .title(title)
                .build();

        return todoRepository.save(todo).getId();
    }

    // 수정 (수행 여부)
    public void updateCheck(Long id){

        TodoList findTodo = findById(id);

        findTodo.updateChecked();
    }

    // 수정 (내용)
    public Long updateTitle(Long id, String title){

        TodoList findTodo = findById(id);

        findTodo.updateTitle(title);

        return id;
    }

    public void delete(Long id){

        todoRepository.deleteById(id);
    }
}
