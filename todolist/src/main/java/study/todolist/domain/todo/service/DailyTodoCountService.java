package study.todolist.domain.todo.service;

import study.todolist.domain.member.entity.Member;

public interface DailyTodoCountService {
    void increaseDailyTodoCount(Member member);
    boolean isDailyTodoLimitExceeded(Member member);
    void resetDailyTodoCounts();
}
