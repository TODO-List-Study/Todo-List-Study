package study.todolist.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;
import study.todolist.entity.member.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    public Optional<Member> findByEmail(String email);
}
