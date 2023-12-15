package study.todolist.presentation.dto.res;

import study.todolist.domain.Category;

import java.time.DayOfWeek;
import java.time.ZonedDateTime;

public record ToDoDetailRes(Long id,
                            String title,
                            String contents,
                            Category category,
                            ZonedDateTime postTime,
                            DayOfWeek dayOfWeek,
                            int viewer) {
}
