package study.todolist.domain.todo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.todolist.base.BaseEntity;
import study.todolist.domain.member.entity.Member;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Todo extends BaseEntity {

    @Column(nullable = false)
    private TodoTask task;
    private Boolean isCompleted = false;
    private Priority priority;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private Boolean isDeleted = false;

    public static Todo of(TodoTask task, Boolean isCompleted, Priority priority, Member member) {
        return new Todo(task, isCompleted, priority, member, false);
    }

    public void setTask(TodoTask task) {
        this.task = task;
        update();
    }

    public void delete() {
        this.isDeleted = true;
    }

    public boolean isDeleted() {
        return this.isDeleted;
    }
}
