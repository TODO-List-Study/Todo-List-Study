package study.todolist.domain.member.repository;

import org.springframework.data.repository.Repository;
import study.todolist.domain.member.entity.Member;

public interface MemberRepository extends Repository<Member, Long> {

}
