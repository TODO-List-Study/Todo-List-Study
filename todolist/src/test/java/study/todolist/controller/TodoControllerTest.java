package study.todolist.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import study.todolist.entity.member.Member;
import study.todolist.entity.todo.Status;
import study.todolist.entity.todo.TodoList;
import study.todolist.service.TodoService;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(TodoController.class)
class TodoControllerTest {

    @MockBean
    private TodoService todoService;

    @Autowired
    private TodoController todoController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void initMockMvc() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(todoController)
                .build();
    }
    @Test
    @DisplayName("단건조회")
    public void 단건조회() throws Exception {

        // given
        Member member = new Member(1L, "Test", null);
        TodoList todo = new TodoList("title", Status.BEFORE, member);

        // stub
        BDDMockito.given(todoService.findById(member.getTodoList().getId())).willReturn(todo);

        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo/find/" + member.getId())
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk());

        // then
        BDDMockito.verify(todoService).findById(member.getId());
    }

    @Test
    @DisplayName("전체조회")
    public void 전체조회() throws Exception {

        // given
        Member member = new Member(1L, "Test", null);
        List<TodoList> response = new ArrayList<>();
        response.add(new TodoList("First", Status.BEFORE, member));
        response.add(new TodoList("Second", Status.BEFORE, member));
        response.add(new TodoList("Third", Status.BEFORE, member));

        // stub
        BDDMockito.given(todoService.findAll()).willReturn(response);

        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo/find/all")
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk());

        // then
        BDDMockito.verify(todoService).findAll();
    }

    @Test
    @DisplayName("삭제")
    public void 삭제() throws Exception {

        // given
        Long id = 1L;

        // stub
        BDDMockito.willDoNothing().given(todoService).delete(id);

        // when
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/todo/delete/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk());

        // then
        BDDMockito.verify(todoService).delete(id);
    }
}