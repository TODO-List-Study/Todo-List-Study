package study.todolist.domain.member.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Builder(toBuilder = true)
public class MemberDto {
    private String username;
    private String password;
    private String email;
    private Collection<? extends GrantedAuthority> roles;
}
