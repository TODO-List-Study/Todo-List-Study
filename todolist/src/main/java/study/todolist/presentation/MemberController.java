package study.todolist.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import study.todolist.application.MemberCommandService;
import study.todolist.global.dto.ResponseEnvelope;
import study.todolist.presentation.dto.req.CreateMemberReq;
import study.todolist.presentation.dto.res.MemberIdRes;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberCommandService memberCommandService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEnvelope<MemberIdRes> createMember(@Valid @RequestBody CreateMemberReq createMemberReq) {
        Long memberId = memberCommandService.signUp(createMemberReq.email(), createMemberReq.password());

        return ResponseEnvelope.of(new MemberIdRes(memberId));
    }
}
