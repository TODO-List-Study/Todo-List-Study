package study.todolist.domain.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.todolist.domain.member.entity.Member;
import study.todolist.domain.member.exception.MemberNotFoundException;
import study.todolist.domain.member.repository.MemberRepository;
import study.todolist.domain.todo.dto.request.TodoRequest;
import study.todolist.domain.todo.dto.response.ViewSingleResponse;
import study.todolist.domain.todo.entity.Priority;
import study.todolist.domain.todo.entity.Todo;
import study.todolist.domain.todo.entity.TodoTask;
import study.todolist.domain.todo.exception.BulkCreateTodoLimitExceededException;
import study.todolist.domain.todo.exception.TodoNotFoundException;
import study.todolist.domain.todo.repository.TodoRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoServiceImpl implements TodoService {

    private static final int MAX_BULK_CREATE_COUNT = 5000;

    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository;

    @Override
    public ViewSingleResponse createTodo(TodoRequest request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new MemberNotFoundException("해당 ID의 회원이 존재하지 않습니다."));

        TodoTask todoTask = TodoTask.from(request.getTask());
        Todo todo = Todo.of(todoTask, false, request.getPriority(), member);
        todoRepository.save(todo);
        return new ViewSingleResponse(todo);
    }

    @Override
    public List<ViewSingleResponse> getAllTodos() {
        return todoRepository.findAll().stream()
                .filter(todo -> !todo.isDeleted())
                .sorted(Comparator.comparing(Todo::getPriority).reversed())
                .map(ViewSingleResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public ViewSingleResponse getSingleTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new TodoNotFoundException("해당 ID의 할 일이 존재하지 않습니다."));
        if (todo.isDeleted()) {
            throw new TodoNotFoundException("해당 ID의 할 일이 삭제되었습니다.");
        }
        return new ViewSingleResponse(todo);
    }

    @Override
    public ViewSingleResponse updateTodo(Long id, String taskStr) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new TodoNotFoundException("해당 ID의 할 일이 존재하지 않습니다."));
        if (todo.isDeleted()) {
            throw new TodoNotFoundException("해당 ID의 할 일이 삭제되었습니다.");
        }
        TodoTask task = TodoTask.from(taskStr);
        todo.setTask(task);
        todoRepository.save(todo);
        return new ViewSingleResponse(todo);
    }

    @Override
    public ViewSingleResponse deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new TodoNotFoundException("해당 ID의 할 일이 존재하지 않습니다."));
        if (todo.isDeleted()) {
            throw new TodoNotFoundException("해당 ID의 할 일은 이미 삭제되었습니다.");
        }
        todo.delete();
        return new ViewSingleResponse(todo);
    }

    @Override
    @Transactional
    public List<ViewSingleResponse> bulkCreateTodo(Integer count) {

        if (count > MAX_BULK_CREATE_COUNT) {
            throw new BulkCreateTodoLimitExceededException("한 번에 생성할 수 있는 Todo의 개수는 " + MAX_BULK_CREATE_COUNT + "개를 넘을 수 없습니다.");
        }

        List<Todo> todos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Member member = getRandomMember();  // Random Member를 가져오는 메소드
            TodoTask todoTask = generateRandomTodoTask();  // Random TodoTask를 생성하는 메소드
            Todo todo = Todo.of(todoTask, false, generateRandomPriority(), member);
            todos.add(todo);
        }

        todoRepository.saveAll(todos);
        return todos.stream().map(ViewSingleResponse::new).collect(Collectors.toList());

    }

    private Member getRandomMember() {
        long count = memberRepository.count();
        long randomId = ThreadLocalRandom.current().nextLong(1, count + 1);

        return memberRepository.findById(randomId).orElseThrow(() -> new MemberNotFoundException("해당 ID의 회원이 존재하지 않습니다."));
    }

    private TodoTask generateRandomTodoTask() {
        String task = "Todo Task " + UUID.randomUUID().toString();
        return TodoTask.from(task);
    }

    private Priority generateRandomPriority() {
        return Priority.values()[ThreadLocalRandom.current().nextInt(Priority.values().length)];

    }
}