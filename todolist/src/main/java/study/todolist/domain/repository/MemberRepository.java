package study.todolist.domain.repository;

import org.springframework.data.repository.Repository;
import study.todolist.domain.Member;

public interface MemberRepository extends Repository<Member, Long> {

    Member save(Member member);
}
