package study.todolist.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.todolist.domain.exception.ToDoNotPostAbleException;

import java.time.DayOfWeek;
import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class ToDoEssential {
    private String title;

    private String contents;

    @Enumerated(EnumType.STRING)
    private Category category;

    private ZonedDateTime postTime;

    private DayOfWeek dayOfWeek;

    public ToDoEssential(String title, String contents, Category category, ZonedDateTime postTime, DayOfWeek dayOfWeek) {
        if (isValid(postTime)) {
            throw new ToDoNotPostAbleException();
        }
        this.title = title;
        this.contents = contents;
        this.category = category;
        this.postTime = postTime;
        this.dayOfWeek = dayOfWeek;
    }


    private boolean isValid(ZonedDateTime postTime) {
        return isPostAble();
    }

    public boolean isPostAble() {
        return postTime.isBefore(ZonedDateTime.now());
    }
}
