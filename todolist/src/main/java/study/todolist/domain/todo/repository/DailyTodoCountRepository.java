package study.todolist.domain.todo.repository;

import org.springframework.data.repository.Repository;
import study.todolist.domain.member.entity.Member;
import study.todolist.domain.todo.entity.DailyTodoCount;

import java.time.LocalDate;
import java.util.Optional;

public interface DailyTodoCountRepository extends Repository<DailyTodoCount, Long> {
    Optional<DailyTodoCount> findByMemberAndDate(Member member, LocalDate date);

    void save(DailyTodoCount dailyTodoCount);

    void deleteAll();
}
