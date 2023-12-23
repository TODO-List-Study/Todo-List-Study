package study.todolist.domain.member.repository;

import org.springframework.stereotype.Repository;
import study.todolist.database.InMemoryDB;
import study.todolist.domain.member.entity.Member;

import java.util.List;
import java.util.Optional;

@Repository
public class MemberRepository {

    private final InMemoryDB<Long, Member> db;

    public MemberRepository(InMemoryDB<Long, Member> db) {
        this.db = db;
    }

    public Optional<Member> findById(Long id) {
        return db.findById(id);
    }

    public Member save(Member member) {
        db.save(member.getId(), member);
        return member;
    }

    public void deleteById(Long id) {
        db.deleteById(id);
    }

    public List<Member> findAll() {
        return db.findAll();
    }

    public Optional<Member> findByUsername(String username) {
        return db.findAll().stream()
                .filter(member -> member.getUsername().equals(username))
                .findFirst();
    }
}
