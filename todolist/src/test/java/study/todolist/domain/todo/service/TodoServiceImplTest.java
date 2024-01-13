package study.todolist.domain.todo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import study.todolist.database.InMemoryDB;
import study.todolist.domain.todo.dto.response.ViewSingleResponse;
import study.todolist.domain.todo.entity.Todo;
import study.todolist.domain.todo.entity.TodoTask;
import study.todolist.domain.todo.exception.NotFoundException;
import study.todolist.domain.todo.entity.Priority;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TodoServiceImplTest {

    @Mock
    private InMemoryDB<Long, Todo> inMemoryDB;

    @InjectMocks
    private TodoServiceImpl todoService;

    private Long id;
    private Todo todo;

    @BeforeEach
    public void setUp() {
        id = 1L;
        todo = Todo.of(TodoTask.from("Task"), false, Priority.MEDIUM);
        when(inMemoryDB.findById(id)).thenReturn(Optional.of(todo));
    }

    @Test
    public void getSingleTodoTest() {
        ViewSingleResponse response = todoService.getSingleTodo(id);
        assertEquals(response.getTask(), todo.getTask().getTask());

        when(inMemoryDB.findById(id)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> todoService.getSingleTodo(id));
    }

    @Test
    public void getAllTodosTest() {
        List<Todo> todos = Arrays.asList(
                Todo.of(TodoTask.from("Task 1"), false, Priority.HIGH),
                Todo.of(TodoTask.from("Task 2"), false, Priority.MEDIUM),
                Todo.of(TodoTask.from("Task 3"), true, Priority.LOW)
        );
        when(inMemoryDB.findAll()).thenReturn(todos);

        List<ViewSingleResponse> responses = todoService.getAllTodos();
        assertEquals(2, responses.size());
        assertEquals("Task 1", responses.get(0).getTask());
        assertEquals("Task 2", responses.get(1).getTask());
    }

    @Test
    public void updateTodoTest() {
        String taskStr = "수정된 할 일";
        ViewSingleResponse response = todoService.updateTodo(id, taskStr);
        assertEquals(response.getTask(), taskStr);

        when(inMemoryDB.findById(id)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> todoService.updateTodo(id, taskStr));
    }

    @Test
    public void deleteTodoTest() {
        todoService.deleteTodo(id);
        assertTrue(todo.isDeleted());

        when(inMemoryDB.findById(id)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> todoService.deleteTodo(id));
    }

}
