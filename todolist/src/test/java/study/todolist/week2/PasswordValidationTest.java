package study.todolist.week2;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import study.todolist.application.MemberCommandService;
import study.todolist.presentation.MemberController;
import study.todolist.presentation.dto.req.CreateMemberReq;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MemberController.class)
@AutoConfigureMockMvc(addFilters = false)
public class PasswordValidationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MemberCommandService memberCommandService;

    @Test
    void whenValidInput_thenReturns200() throws Exception {
        CreateMemberReq createMemberReq = new CreateMemberReq("test@email.com", "A1234567!");

        mockMvc.perform(MockMvcRequestBuilders.post("/members/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createMemberReq)))
                .andExpect(status().isCreated());
    }

    @Test
    void whenInvalidInput_thenReturns400() throws Exception {
        CreateMemberReq createMemberReq = new CreateMemberReq("test@email.com", "1234");

        mockMvc.perform(MockMvcRequestBuilders.post("/members/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createMemberReq)))
                .andExpect(status().isBadRequest());
    }
}
