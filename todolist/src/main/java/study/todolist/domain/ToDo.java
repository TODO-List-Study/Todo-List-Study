package study.todolist.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID uuid;

    private ToDoEssential toDoEssential;

    private int viewer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id")
    private Member member;

    public ToDo(UUID uuid, String title, String contents, Category category, ZonedDateTime postTime, int viewer, Member member) {
        this.uuid = uuid;
        this.toDoEssential = new ToDoEssential(title, contents, category, postTime, postTime.getDayOfWeek());
        this.viewer = viewer;
        this.member = member;
    }
}
