package study.todolist.domain.member.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import study.todolist.domain.member.entity.Member;

import java.util.ArrayList;
import java.util.Collection;

@Getter
public class CustomUserDetails implements UserDetails {
    private String username;                                     // DB의 P.K
    private String password;                                     // DB의 비밀번호
    private boolean accountNonLocked = true;                      // 계정 잠금 여부
    private boolean accountNonExpired = true;                    // 사용자 계정 만료 없음
    private boolean credentialsNonExpired = true;                // 비밀번호 만료 없음
    private boolean enabled = true;                               // 사용자 활성화 여부
    private Collection<? extends GrantedAuthority> authorities;  // 사용자 권한 목록

    public CustomUserDetails(Member member) {
        this.username = member.getUsername();
        this.password = member.getPassword();
        this.authorities = new ArrayList<>();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
