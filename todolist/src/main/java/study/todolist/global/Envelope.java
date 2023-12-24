package study.todolist.global;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Envelope<T> {

    private T data;
    private int error;
    private String message;

    public static <T> Envelope<T> of (T data, int error, String message){

        return new Envelope<>(data, error, message);
    }

    public static <T> Envelope<T> success (T data){

        return of(data, HttpStatus.OK.value(), "성공");
    }

    public static <T> Envelope<T> fail (int error, String message){

        return of(null, error, message);
    }
}