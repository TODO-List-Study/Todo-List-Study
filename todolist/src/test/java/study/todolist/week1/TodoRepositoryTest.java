package study.todolist.week1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.todolist.domain.Category;
import study.todolist.domain.Member;
import study.todolist.domain.ToDo;
import study.todolist.domain.ToDoEssential;
import study.todolist.domain.repository.Repository;
import study.todolist.domain.repository.ToDoRepository;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class TodoRepositoryTest {

    @Autowired
    private ToDoRepository toDoRepository;

    @BeforeEach
    public void setUpDummy(){
        for (int i=1;i<=10;i++){
            ToDo toDo = new ToDo("test title" + i, "test contents" + i, Category.SIMPLE, LocalDateTime.now(), 0, new Member());
            toDoRepository.save(1L, toDo);
        }
    }

    @Test
    @DisplayName("DB 저장시 ID 값 증가 테스트")
    public void todoRepositoryIdAutoIncrementTest(){
        //given
        ToDo toDo = new ToDo("test title" + 11, "test contents" + 11, Category.SIMPLE, LocalDateTime.now(), 0, new Member());

        //when
        ToDo saveToDo = toDoRepository.save(1L, toDo);

        //then
        assertThat(saveToDo.hashCode()).isEqualTo(toDoRepository.findById(11L).hashCode());
    }
}
