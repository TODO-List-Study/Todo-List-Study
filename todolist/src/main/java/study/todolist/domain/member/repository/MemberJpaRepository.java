package study.todolist.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.todolist.domain.member.entity.Member;

import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);
}
