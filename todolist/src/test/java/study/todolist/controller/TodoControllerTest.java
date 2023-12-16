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
import study.todolist.dto.TodoDto;
import study.todolist.entity.TodoList;
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
        Long id = 1L;
        TodoList todo = new TodoList(id, "title", false);

        // stub
        BDDMockito.given(todoService.findById(id)).willReturn(todo);

        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo/find/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk());

        // then
        BDDMockito.verify(todoService).findById(id);
    }

    @Test
    @DisplayName("전체조회")
    public void 전체조회() throws Exception {

        // given
        List<TodoList> response = new ArrayList<>();
        response.add(new TodoList(1L, "First", false));
        response.add(new TodoList(2L, "Second", false));
        response.add(new TodoList(3L, "Third", false));

        // stub
        BDDMockito.given(todoService.findAll()).willReturn(response);

        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo/find/all")
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk());

        // then
        BDDMockito.verify(todoService).findAll();
    }
}