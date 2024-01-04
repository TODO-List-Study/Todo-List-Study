package study.todolist.entity.member;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.todolist.entity.todo.TodoList;

import java.util.List;

@Entity @Getter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Member {

    @Id @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private String password;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "member")
    private List<TodoList> todoList;
}
