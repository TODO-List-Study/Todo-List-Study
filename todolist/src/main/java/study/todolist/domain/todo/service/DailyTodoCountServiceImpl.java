package study.todolist.domain.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.todolist.domain.member.entity.Member;
import study.todolist.domain.todo.entity.DailyTodoCount;
import study.todolist.domain.todo.repository.DailyTodoCountRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DailyTodoCountServiceImpl implements DailyTodoCountService {

    private final DailyTodoCountRepository dailyTodoCountRepository;

    @Override
    public void increaseDailyTodoCount(Member member) {
        LocalDate today = LocalDate.now();

        DailyTodoCount dailyTodoCount = dailyTodoCountRepository.findByMemberAndDate(member, today)
                .orElse(new DailyTodoCount(member, today, 0));

        dailyTodoCount.setCount(dailyTodoCount.getCount() + 1);
        dailyTodoCountRepository.save(dailyTodoCount);
    }

    @Override
    public boolean isDailyTodoLimitExceeded(Member member) {
        LocalDate today = LocalDate.now();

        return dailyTodoCountRepository.findByMemberAndDate(member, today)
                .map(dailyTodoCount -> dailyTodoCount.getCount() >= 10)
                .orElse(false);
    }

    @Override
    @Scheduled(cron = "0 0 0 * * ?")
    public void resetDailyTodoCounts() {
        dailyTodoCountRepository.deleteAll();
    }

}
