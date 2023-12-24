package study.todolist.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.todolist.domain.Member;
import study.todolist.domain.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandService {

    private final MemberRepository memberRepository;

    public Long createMember(String email, String password) {
        Member member = new Member(email, password);
        memberRepository.save(member);
        
        return member.getId();
    }
}
