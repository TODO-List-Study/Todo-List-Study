package study.todolist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.todolist.dto.MemberDto;
import study.todolist.dto.TokenDto;
import study.todolist.entity.member.Member;
import study.todolist.global.error.ErrorCode;
import study.todolist.global.error.exception.BusinessException;
import study.todolist.global.error.exception.EntityNotFoundException;
import study.todolist.global.token.TokenManager;
import study.todolist.repository.member.MemberRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final TokenManager tokenManager;

    private void validateDuplicate(String email){

        Optional<Member> findByEmail = memberRepository.findByEmail(email);

        if (findByEmail.isPresent())
            throw new BusinessException(ErrorCode.ALREADY_REGISTERED_MEMBER);
    }

    public Member findById(Long id){

        return memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUNT_MEMBER));
    }

    public Member findByEmailAndPassword(String email, String password){

        Member findMember = memberRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUND_TODO));

        if (!findMember.getPassword().equals(password))
            throw new EntityNotFoundException(ErrorCode.NOT_FOUNT_MEMBER);

        return findMember;
    }

    public List findAll(){

        return memberRepository.findAll().stream()
                .map(MemberDto.Response::of)
                .collect(Collectors.toList());
    }

    public Long signUp(String username, String email, String password){

        validateDuplicate(email);

        Member member = Member.builder()
                .username(username)
                .email(email)
                .password(password)
                .build();

        memberRepository.save(member);

        return member.getId();
    }

    public void deleteMember(Long id){

        memberRepository.delete(findById(id));
    }

    public MemberDto.Response signIn(String email, String password){

        Member findMember = findByEmailAndPassword(email, password);

        TokenDto tokenDto = tokenManager.createJwtTokenDto(findMember.getId(), findMember.getUsername());
        findMember.updateToken(tokenDto);

        return MemberDto.Response.of(findMember);
    }
}
