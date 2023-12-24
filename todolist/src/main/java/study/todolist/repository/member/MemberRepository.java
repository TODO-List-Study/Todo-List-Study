package study.todolist.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;
import study.todolist.entity.member.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
