package study.todolist.domain.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import study.todolist.base.RsData;
import study.todolist.base.jwt.dto.TokenDto;
import study.todolist.domain.member.dto.JoinRequest;
import study.todolist.domain.member.dto.LoginDto;
import study.todolist.domain.member.entity.Member;
import study.todolist.domain.member.service.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vi/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public RsData<Member> join(@Valid @RequestBody JoinRequest joinRequest) {
        Member user = memberService.createMember(joinRequest);
        return RsData.successOf(user);
    }

    @GetMapping("/{id}")
    public RsData<Member> getMember(@PathVariable Long id) {
        Member member = memberService.findById(id);
        return RsData.successOf(member);
    }

    @PostMapping("/login")
    public RsData<TokenDto> login(@Valid @RequestBody LoginDto loginDto) throws Exception {

        TokenDto tokenDto = memberService.login(loginDto);

        return RsData.successOf(tokenDto);
    }

    /*
    @PostMapping("/dup-username")
    public RsData<Member> checkDupUsername(@Valid @RequestBody UsernameRequest usernameRequest) {

        if (memberService.isDuplicateUsername(usernameRequest)) {
            return RsData.of("F-1", "이미 중복된 아이디가 있습니다.");
        }

        return RsData.of("S-1", "사용가능한 아이디입니다.");
    }
     */
}
