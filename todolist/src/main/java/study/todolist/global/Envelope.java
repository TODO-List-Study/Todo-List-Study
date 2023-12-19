package study.todolist.global;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import study.todolist.dto.TodoDto;
import study.todolist.entity.TodoList;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Envelope<T> {

    private T data;
    private HttpStatus error;
    private String message;

    public static <T> Envelope<T> of (T data, HttpStatus error, String message){

        return new Envelope<>(data, error, message);
    }

    public static <T> Envelope<T> success (T data){

        return of(data, HttpStatus.OK, "성공");
    }
}