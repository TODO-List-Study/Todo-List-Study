package study.todolist.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ToDo {
    private Long id;

    private ToDoEssential toDoEssential;

    private int viewer;

    private Member member;

    public ToDo(String title, String contents, Category category, LocalDateTime postTime, int viewer, Member member) {
        this.toDoEssential = new ToDoEssential(title, contents, category, postTime);
        this.viewer = viewer;
        this.member = member;
    }
}
