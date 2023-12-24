package study.todolist.domain.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import study.todolist.base.BaseEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class Member extends BaseEntity {
    @Column(unique = true)
    private String username;
    private String password;

    private int dailyTodoCount = 0;

    public Collection<? extends GrantedAuthority> getGrantedAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        // 모든 멤버는 member 권한을 가진다.
        grantedAuthorities.add(new SimpleGrantedAuthority("member"));

        return grantedAuthorities;
    }

    public void increaseDailyTodoCount() {
        this.dailyTodoCount++;
    }

    public void resetDailyTodoCount() {
        this.dailyTodoCount = 0;
    }

    public boolean isDailyTodoLimitExceeded() {
        return this.dailyTodoCount >= 10;
    }
}
