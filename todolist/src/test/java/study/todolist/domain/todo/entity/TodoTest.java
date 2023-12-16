package study.todolist.domain.todo.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import study.todolist.domain.todo.util.Priority;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TodoTest {

    @Test
    @DisplayName("할 일 생성 시 ID가 증가한다.")
    public void createTodoTest() {
        String taskStr = "모두 2023년 수고 많으셨습니다 !";
        Todo todo1 = Todo.of(TodoTask.from(taskStr), false, Priority.MEDIUM);
        Todo todo2 = Todo.of(TodoTask.from(taskStr), false, Priority.MEDIUM);

        assertNotNull(todo1.getId());
        assertNotNull(todo2.getId());
        assertNotEquals(todo1.getId(), todo2.getId());
        assertTrue(todo2.getId() > todo1.getId());
    }

    @Test
    @DisplayName("task에 빈 문자열이나 공백 문자열을 입력하면 예외가 발생한다.")
    public void createTodoTaskTest() {
        String taskStr = "2024년 새해 복 많이 받으세요";
        TodoTask todoTask = TodoTask.from(taskStr);
        assertEquals(taskStr, todoTask.getTask());

        assertThrows(IllegalArgumentException.class, () -> TodoTask.from(""));
        assertThrows(IllegalArgumentException.class, () -> TodoTask.from("   "));
    }
}
