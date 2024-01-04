package study.todolist.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.todolist.domain.Member;
import study.todolist.domain.exception.EmailDuplicationException;
import study.todolist.domain.exception.MemberNotFoundException;
import study.todolist.domain.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandService {

    private final MemberRepository memberRepository;

    public Long signUp(String email, String password) {
        Member member = new Member(email, password);

        if (memberRepository.existsByEmail(email)) {
            throw new EmailDuplicationException();
        }
        memberRepository.save(member);

        return member.getId();
    }

    public void signOutByEmail(String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);

        memberRepository.delete(member);
    }
}
