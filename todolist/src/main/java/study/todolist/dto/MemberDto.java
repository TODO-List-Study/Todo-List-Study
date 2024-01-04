package study.todolist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.todolist.entity.member.Member;

public class MemberDto {

    @Data
    public static class Request{

        private String email;

        private String password;
    }

    @Data @Builder
    @NoArgsConstructor @AllArgsConstructor
    public static class Response{

        private Long id;

        private String username;

        private String email;

        public static Response of(Member member){

            return Response.builder()
                    .id(member.getId())
                    .username(member.getUsername())
                    .email(member.getEmail())
                    .build();
        }
    }
}
