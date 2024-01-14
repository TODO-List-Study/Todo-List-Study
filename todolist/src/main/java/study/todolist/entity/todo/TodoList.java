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
    private Integer viewer;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public void updateTitle(String title){
        this.title = title;
    }

    public void updateStatus(Status status){
        this.status = status;
    }
    public void updateViewer(){

        this.viewer += 1;
    }

    @Builder
    public TodoList(String title, Status status, Integer viewer, Member member) {
        this.title = title;
        this.status = status;
        this.viewer = viewer;
        this.member = member;
    }
}

