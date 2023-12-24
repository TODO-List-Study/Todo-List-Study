package study.todolist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.todolist.dto.TodoDto;
import study.todolist.entity.member.Member;
import study.todolist.entity.todo.Status;
import study.todolist.entity.todo.TodoList;
import study.todolist.global.error.ErrorCode;
import study.todolist.global.error.exception.EntityNotFoundException;
import study.todolist.global.error.exception.OverMaxCountException;
import study.todolist.repository.todo.TodoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TodoService {

    private static final Integer MAX_COUNT = 5000;

    private final TodoRepository todoRepository;
    private final MemberService memberService;

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
    @Transactional
    public TodoList createTodo(String title){

        TodoList todo = TodoList.builder()
                .title(title)
                .status(Status.BEFORE)
                .build();

        return todoRepository.save(todo);
    }

    @Transactional
    public List<TodoDto.Response> bulkTodo(Long id, Integer count){

        if (count > MAX_COUNT)
            throw new OverMaxCountException(ErrorCode.OVER_MAX_COUNT);

        Member findMember = memberService.findById(id);

        List<TodoList> todos = new ArrayList<>();
        Random random = new Random();

        for (int i=0; i<count; i++){

            String title = "Title " + random.nextInt(count);
            Status status = Status.values()[random.nextInt(Status.values().length)];

            TodoList todo = TodoList.builder()
                    .title(title)
                    .status(status)
                    .member(findMember)
                    .build();

            todos.add(todo);
        }

        return todos.stream()
                .map(TodoDto.Response::of)
                .collect(Collectors.toList());
    }

    // 수정 (수행 여부)
    @Transactional
    public void updateStatus(Long id, Status status){

        TodoList findTodo = findById(id);

        findTodo.updateStatus(status);
    }

    // 수정 (내용)
    @Transactional
    public Long updateTitle(Long id, String title){

        TodoList findTodo = findById(id);

        findTodo.updateTitle(title);

        return id;
    }
    @Transactional
    public void delete(Long id){

        TodoList findTodo = findById(id);

        todoRepository.delete(findTodo);
    }
}
