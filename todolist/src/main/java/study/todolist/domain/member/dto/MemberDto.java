package study.todolist.domain.member.dto;

import lombok.Builder;
import lombok.Getter;
import study.todolist.domain.member.entity.Member;

@Getter
@Builder(toBuilder = true)
public class MemberDto {
    private Long id;
    private String username;
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Member toEntity() {
        return Member.builder()
                .id(this.toEntity().getId())
                .username(this.username)
                .password(this.password)
                .build();
    }
}
