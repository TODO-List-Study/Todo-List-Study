package study.todolist.entity.member;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.todolist.dto.TokenDto;
import study.todolist.entity.todo.TodoList;
import study.todolist.global.DateTimeUtils;

import java.time.LocalDateTime;
import java.util.List;

@Entity @Getter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Member {

    @Id @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private String password;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "member")
    private List<TodoList> todoList;

    @Column(length = 250)
    private String refreshToken;

    private LocalDateTime tokenExpirationTime;

    public void updateToken(TokenDto dto){

        this.refreshToken = dto.getRefreshToken();
        this.tokenExpirationTime = DateTimeUtils.convertToLocalTimeZone(dto.getRefreshTokenExpireTime());
    }

    public void expireToken(LocalDateTime now){

        this.tokenExpirationTime = now;
    }
}
