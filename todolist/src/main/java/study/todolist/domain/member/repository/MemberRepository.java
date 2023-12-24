package study.todolist.domain.member.repository;

import org.springframework.data.repository.Repository;
import study.todolist.domain.member.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends Repository<Member, Long> {
    Optional<Member> findByUsername(String username);
    Optional<Member> findById(Long id);
    Member save(Member member);
    List<Member> findAll();
    void deleteById(Long id);
    long count();
}
