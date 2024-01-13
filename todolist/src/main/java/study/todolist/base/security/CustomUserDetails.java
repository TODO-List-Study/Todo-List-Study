package study.todolist.base.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import study.todolist.domain.member.dto.MemberDto;

import java.util.Collection;

@Getter
public class CustomUserDetails implements UserDetails {

    private String username;                                     // DB의 P.K
    private String password;                                     // DB의 비밀번호
    private boolean accountNonLocked =true;                      // 계정 잠금 여부
    private boolean accountNonExpired =true ;                    // 사용자 계정 만료 없음
    private boolean credentialsNonExpired =true ;                // 비밀번호 만료 없음
    private boolean enabled =true;                               // 사용자 활성화 여부
    private Collection<? extends GrantedAuthority> authorities;  // 사용자 권한 목록
    private String nickname;

    public CustomUserDetails(MemberDto dto) {
        this.username = dto.getEmail();
        this.password = dto.getPassword();
        this.nickname = dto.getUsername();
        this.authorities = dto.getRoles();
    }
}
