package study.todolist.domain.todo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.todolist.domain.member.entity.Member;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class DailyTodoCount {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Member member;

    private LocalDate date;

    private int count;

    public void setId(Long id) {
        this.id = id;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public DailyTodoCount(Member member, LocalDate date, int count) {
        this.member = member;
        this.date = date;
        this.count = count;
    }
}
