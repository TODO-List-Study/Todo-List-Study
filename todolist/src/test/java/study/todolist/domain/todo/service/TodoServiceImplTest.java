package study.todolist.domain.todo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import study.todolist.domain.todo.dto.request.TodoRequest;
import study.todolist.domain.todo.dto.response.ViewSingleResponse;
import study.todolist.domain.todo.util.Priority;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TodoServiceImplTest {
    private TodoServiceImpl todoService;

    @BeforeEach
    void setUp() {
        todoService = new TodoServiceImpl(100);  // 캐시의 최대 크기를 100으로 설정

        todoService.createTodo(new TodoRequest("Task 1", "HIGH"));
        todoService.createTodo(new TodoRequest("Task 2", "LOW"));
        todoService.createTodo(new TodoRequest("Task 3", "MEDIUM"));

    }

    @Test
    @DisplayName("할 일 생성")
    void createTodo() {
        String taskStr = "Test Task";
        String priorityStr = "HIGH";
        TodoRequest request = new TodoRequest(taskStr, priorityStr);
        ViewSingleResponse result = todoService.createTodo(request);

        assertNotNull(result, "반환된 객체는 null이 아니어야 한다.");
        assertEquals(taskStr, result.getTask(), "반환된 객체의 task는 입력과 일치해야 한다");
        assertEquals(Priority.valueOf(priorityStr), result.getPriority(), "반환된 객체의 priority는 입력과 일치해야 한다");
    }

    @Test
    @DisplayName("할 일 수정")
    void updateTodo() {
        // GIVEN
        String taskStr = "Test Task";
        String priorityStr = "HIGH";
        TodoRequest createRequest = new TodoRequest(taskStr, priorityStr);
        ViewSingleResponse createResult = todoService.createTodo(createRequest);

        // WHEN
        String newTaskStr = "Updated Task";
        Optional<ViewSingleResponse> updateResultOptional = todoService.updateTodo(createResult.getId(), newTaskStr);

        // THEN
        assertTrue(updateResultOptional.isPresent(), "수정된 객체가 존재해야 한다");
        ViewSingleResponse updateResult = updateResultOptional.get();
        assertEquals(newTaskStr, updateResult.getTask(), "수정된 객체의 task는 새로운 입력과 일치해야 한다");
    }

    @Test
    @DisplayName("할 일 삭제")
    void deleteTodo() {
        // GIVEN
        String taskStr = "Test Task";
        String priorityStr = "HIGH";
        TodoRequest createRequest = new TodoRequest(taskStr, priorityStr);
        ViewSingleResponse createResult = todoService.createTodo(createRequest);

        // WHEN
        Optional<ViewSingleResponse> deleteResultOptional = todoService.deleteTodo(createResult.getId());

        // THEN
        assertTrue(deleteResultOptional.isPresent(), "삭제된 객체가 존재해야 한다");
        ViewSingleResponse deleteResult = deleteResultOptional.get();
        assertTrue(deleteResult.isDeleted(), "삭제된 객체의 isDeleted는 true이어야 한다");

        // 삭제한 할 일을 다시 조회하려고 시도
        Optional<ViewSingleResponse> notFoundResultOptional = todoService.getSingleTodo(createResult.getId());
        assertFalse(notFoundResultOptional.isPresent(), "삭제된 객체는 조회되지 않아야 한다");
    }

    @Test
    @DisplayName("모든 할 일 중요도 순으로 조회")
    public void testGetAllTodos() {
        List<ViewSingleResponse> todos = todoService.getAllTodos();
        assertEquals(3, todos.size());
        assertEquals("Task 1", todos.get(0).getTask());
        assertEquals("Task 3", todos.get(1).getTask());
        assertEquals("Task 2", todos.get(2).getTask());
    }

    @Test
    @DisplayName("단일 할 일 조회")
    public void testGetSingleTodo() {
        Optional<ViewSingleResponse> todo = todoService.getSingleTodo(1L);
        assertTrue(todo.isPresent());
        assertEquals("Task 1", todo.get().getTask());

        todo = todoService.getSingleTodo(5L);
        assertFalse(todo.isPresent());
    }

    @Test
    @DisplayName("삭제된 할 일이 조회되지 않는지 테스트")
    public void testGetSingleTodoAfterDelete() {
        todoService.deleteTodo(1L);
        Optional<ViewSingleResponse> todo = todoService.getSingleTodo(1L);
        assertFalse(todo.isPresent());
    }
}
