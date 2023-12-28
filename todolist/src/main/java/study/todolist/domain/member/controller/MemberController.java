package study.todolist.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import study.todolist.base.RsData;
import study.todolist.domain.member.dto.MemberDto;
import study.todolist.domain.member.entity.Member;
import study.todolist.domain.member.service.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public RsData<Member> createMember(@RequestBody MemberDto memberDto) {
        Member member = memberService.createMember(memberDto);
        return RsData.successOf(member);
    }

    @GetMapping("/{id}")
    public RsData<Member> getMember(@PathVariable Long id) {
        Member member = memberService.findById(id);
        return RsData.successOf(member);
    }
}
