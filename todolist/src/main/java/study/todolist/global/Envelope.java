package study.todolist.global;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Envelope<T> {

    private T data;
    private String error;
    private String message;
}