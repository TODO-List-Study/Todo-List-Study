package study.todolist.domain.member.repository;

import study.todolist.domain.member.entity.Member;

import java.util.Optional;

public interface MemberRepository {
    Optional<Member> findByUsername(String username);
    Optional<Member> findById(Long id);
    Member save(Member member);
    long count();
    Optional<Member> findByEmail(String email);
}
