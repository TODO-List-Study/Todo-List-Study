package study.todolist.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import study.todolist.global.exception.ErrorCode;

@Getter
@AllArgsConstructor
public class RsData<T> {
    private String status;
    private String message;
    private T data;

    public static <T> RsData<T> of(ErrorCode errorCode) {
        return new RsData<>(errorCode.getStatus().toString(), errorCode.getMessage(), null);
    }

    public static <T> RsData<T> of(ErrorCode errorCode, T data) {
        return new RsData<>(errorCode.getStatus().toString(), errorCode.getMessage(), data);
    }

    public static <T> RsData<T> successOf(T data) {
        return of(ErrorCode.OK, data);
    }

    public static <T> RsData<T> failOf(T data) {
        return of(ErrorCode.INTERNAL_SERVER_ERROR, data);
    }

    public static <T> RsData<T> failOf(String message) {
        return new RsData<>("F-1", message, null);
    }

    public boolean isSuccess() {
        return HttpStatus.OK.toString().equals(this.status);
    }

    public boolean isFail() {
        return !isSuccess();
    }

}
