package study.todolist.domain.member.service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import study.todolist.base.jwt.dto.TokenDto;
import study.todolist.base.jwt.service.TokenService;
import study.todolist.domain.member.dto.JoinRequest;
import study.todolist.domain.member.dto.LoginDto;
import study.todolist.domain.member.dto.UsernameRequest;
import study.todolist.domain.member.entity.AuthLevel;
import study.todolist.domain.member.entity.Member;
import study.todolist.domain.member.exception.NotMatchPasswordException;
import study.todolist.domain.member.repository.MemberRepository;

import javax.security.auth.login.AccountNotFoundException;

@Service
@Builder
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public Member findById(long id) {
        return memberRepository.findById(id).orElseThrow();
    }

    public Member createMember (JoinRequest joinRequest){
        Member member = Member.builder().username(joinRequest.getUsername())
                .password(joinRequest.getPassword())
                .email(joinRequest.getEmail())
                .authLevel(AuthLevel.MEMBER)
                .build();

        member.encryptPassword(passwordEncoder);

        return memberRepository.save(member);
    }

    public boolean isDuplicateUsername (UsernameRequest usernameRequest){
        String username = usernameRequest.getUsername();
        return memberRepository.findByUsername(username).isPresent();
    }

    public TokenDto login (LoginDto loginDto) throws Exception {

        Member member = memberRepository.findByUsername(loginDto.getUsername())
                .orElseThrow(() -> new AccountNotFoundException("유저를 찾을 수 없습니다."));

        boolean matches = passwordEncoder.matches(loginDto.getPassword(), member.getPassword());
        if (!matches) {
            throw new NotMatchPasswordException("비밀번호가 일치하지 않습니다.");
        }

        return tokenService.provideTokenWithLoginDto(loginDto);
    }

    public Member findByUsername (String username) throws Exception {
        return memberRepository.findByUsername(username)
                .orElseThrow(() -> new AccountNotFoundException("User not Found"));
    }
}
