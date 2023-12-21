package study.todolist.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ToDo> toDos;

    public Member(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Member(Long id) {
        this.id = id;
    }

}
