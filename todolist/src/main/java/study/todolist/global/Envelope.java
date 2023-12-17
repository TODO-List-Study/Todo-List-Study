package study.todolist.global;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.todolist.dto.TodoDto;
import study.todolist.entity.TodoList;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Envelope<T> {

    private T data;
    private String error;
    private String message;

    public static Envelope toEnvelope(TodoList data){

        TodoDto.Response response = TodoDto.Response.builder()
                .id(data.getId())
                .title(data.getTitle())
                .check(data.isChecked())
                .build();

        return Envelope.builder()
                .data(response)
                .build();
    }

    public static Envelope toEnvelope(List<TodoList> data){

        List<TodoDto.Response> list = data.stream()
                .map(TodoDto.Response::of)
                .collect(Collectors.toList());

        return Envelope.builder()
                .data(list)
                .build();
    }
}