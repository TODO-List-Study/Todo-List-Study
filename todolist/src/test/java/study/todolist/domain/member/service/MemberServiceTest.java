package study.todolist.domain.member.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import study.todolist.domain.member.dto.MemberDto;
import study.todolist.domain.member.entity.Member;
import study.todolist.domain.member.repository.MemberRepository;
import study.todolist.global.validator.PasswordValidator;
import study.todolist.member.FakeMemberRepository;
import study.todolist.member.FakePasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import study.todolist.domain.member.repository.MemberRepository;
import study.todolist.domain.member.service.MemberService;
import study.todolist.domain.member.dto.MemberDto;
import study.todolist.global.validator.PasswordValidator;

import static org.mockito.Mockito.*;

public class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private PasswordValidator passwordValidator;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createMember() {
        MemberDto memberDto = new MemberDto();
        memberDto.setUsername("test");
        memberDto.setPassword("Test@1234");

        when(passwordValidator.validate(memberDto.getPassword())).thenReturn(true);
        when(passwordEncoder.encode(memberDto.getPassword())).thenReturn("encoded_password");

        memberService.createMember(memberDto);

        verify(memberRepository, times(1)).save(any());
    }
}
