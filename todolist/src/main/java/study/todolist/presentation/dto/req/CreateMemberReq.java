package study.todolist.presentation.dto.req;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import study.todolist.global.annotation.PasswordValidate;

public record CreateMemberReq(@NotBlank @Email String email,
                              @NotBlank @PasswordValidate String password) {
}
