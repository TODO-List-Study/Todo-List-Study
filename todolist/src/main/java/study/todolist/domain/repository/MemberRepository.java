package study.todolist.domain.repository;

import org.springframework.data.repository.Repository;
import study.todolist.domain.Member;

import java.util.Optional;

public interface MemberRepository extends Repository<Member, Long> {

    Member save(Member member);

    boolean existsByEmail(String email);

    Optional<Member> findByEmail(String email);

    void delete(Member member);
}
