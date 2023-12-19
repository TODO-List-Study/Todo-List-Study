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

    public static <T> Envelope<T> of (T data, String error, String message){

        return new Envelope<>(data, error, message);
    }
}