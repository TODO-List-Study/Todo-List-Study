package study.todolist.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TodoList{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Enumerated(value = EnumType.STRING)
    private Status status;

    public void updateTitle(String title){
        this.title = title;
    }

    public void updateStatus(Status status){
        this.status = status;
    }

    @Builder
    public TodoList(String title, Status status) {
        this.title = title;
        this.status = status;
    }
}

