package study.todolist.presentation.dto.req;

import study.todolist.global.annotation.PasswordValidate;

public record CreateMemberReq(String email, @PasswordValidate String password) {
}
