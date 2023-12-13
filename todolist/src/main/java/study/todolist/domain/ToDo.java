package study.todolist.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ToDo {
    private Long id;

    private ToDoEssential toDoEssential;

    private int viewer;

    private Member member;

    public ToDo(ToDoEssential toDoEssential, int viewer, Member member) {
        this.toDoEssential = toDoEssential;
        this.viewer = viewer;
        this.member = member;
    }
}
