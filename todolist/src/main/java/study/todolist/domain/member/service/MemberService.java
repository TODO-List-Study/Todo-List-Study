package study.todolist.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.todolist.domain.member.dto.MemberDto;
import study.todolist.domain.member.entity.Member;
import study.todolist.domain.member.exception.MemberNotFoundException;
import study.todolist.domain.member.repository.MemberRepository;
import study.todolist.domain.member.security.CustomUserDetails;
import study.todolist.global.validator.PasswordValidator;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final PasswordValidator passwordValidator;

    public Member findById(long id) {
        return memberRepository.findById(id).orElseThrow();
    }

    @Transactional
    public Member createMember(MemberDto memberDto) {

        if (!passwordValidator.validate(memberDto.getPassword())) {
            throw new IllegalArgumentException("대문자, 특수문자 포함하여 8자 이상으로 입력해주세요.");
        }

        Member member = Member.builder()
                .username(memberDto.getUsername())
                .password(passwordEncoder.encode(memberDto.getPassword()))
                .build();

        return memberRepository.save(member);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws MemberNotFoundException {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new MemberNotFoundException("[ " + username + " ]" + "에 해당하는 회원이 없습니다."));

        return new CustomUserDetails(member);
    }
}
