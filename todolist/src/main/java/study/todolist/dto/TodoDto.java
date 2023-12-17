package study.todolist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.todolist.entity.TodoList;

public class TodoDto {

    @Data
    public static class Request{
        private String title;
    }

    @Data @Builder
    @NoArgsConstructor @AllArgsConstructor
    public static class Response{
        private Long id;
        private String title;
        private boolean check;

        public static Response of(TodoList todoList) {

            return Response.builder()
                    .id(todoList.getId())
                    .title(todoList.getTitle())
                    .check(todoList.isChecked())
                    .build();
        }
    }
}

