package study.todolist.controller.member;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import study.todolist.dto.MemberDto;
import study.todolist.entity.member.Member;
import study.todolist.global.Envelope;
import study.todolist.global.token.TokenManager;
import study.todolist.service.MemberService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    private final TokenManager tokenManager;

    @GetMapping("/find/all")
    public Envelope findAll(){

        return Envelope.success(memberService.findAll());
    }

    @GetMapping("/find/{id}")
    public Envelope findOne(@PathVariable Long id){

        Member findMember = memberService.findById(id);

        return Envelope.success(findMember);
    }

    @PostMapping("/signup")
    public Envelope signUp(@RequestBody MemberDto.Request request){

        Long id = memberService.signUp(request.getUsername(), request.getEmail(), request.getPassword());

        return Envelope.success(id);
    }

    @PostMapping("/signin")
    public Envelope signIn(@RequestBody MemberDto.Request request){

        MemberDto.Response response = memberService.signIn(request.getEmail(), request.getPassword());

        return Envelope.success(response);
    }

    @PostMapping("/logout")
    public Envelope logOut(HttpServletRequest request){

        String authorization = request.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);
        Long memberId = Long.parseLong(tokenManager.getTokenClaims(accessToken).getId());

        memberService.findById(memberId).expireToken(LocalDateTime.now());

        return Envelope.success("Logout"); // todo 수정 필요
    }

    @DeleteMapping("/delete/{id}")
    public Envelope deleteMember(@PathVariable("id") Long id){

        memberService.deleteMember(id);

        return Envelope.success("Delete Member"); // todo 수정 필요
    }

}
