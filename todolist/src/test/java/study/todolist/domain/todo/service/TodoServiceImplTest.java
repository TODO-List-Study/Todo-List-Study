package study.todolist.domain.todo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import study.todolist.domain.todo.dto.request.TodoRequest;
import study.todolist.domain.todo.dto.response.ViewSingleResponse;
import study.todolist.domain.todo.util.Priority;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TodoServiceImplTest {
    private TodoServiceImpl todoService;

    @BeforeEach
    void setUp() {
        todoService = new TodoServiceImpl(100);  // 캐시의 최대 크기를 100으로 설정
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
}
