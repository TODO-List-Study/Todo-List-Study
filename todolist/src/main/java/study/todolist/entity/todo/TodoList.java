package study.todolist.entity.todo;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.todolist.entity.member.Member;

@Entity
@Getter
@NoArgsConstructor
public class TodoList{

    @Id @Column(name = "todo_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @OneToOne(mappedBy = "todoList")
    private Member member;

    public void updateTitle(String title){
        this.title = title;
    }

    public void updateStatus(Status status){
        this.status = status;
    }

    @Builder
    public TodoList(String title, Status status, Member member) {
        this.title = title;
        this.status = status;
        this.member = member;
    }
}

