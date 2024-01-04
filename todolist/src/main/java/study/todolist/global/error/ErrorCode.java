package study.todolist.global.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    NOT_FOUND_TODO(HttpStatus.NOT_FOUND, "T-001", "TODO를 찾을 수 없습니다."),
    OVER_MAX_COUNT(HttpStatus.BAD_REQUEST, "T-002", "한번에 생성 가능한 수를 초과하였습니다."),

    NOT_FOUNT_MEMBER(HttpStatus.NOT_FOUND, "M-001", "Member를 찾을 수 없습니다."),
    ALREADY_REGISTERED_MEMBER(HttpStatus.BAD_REQUEST, "M-002", "이미 가입되어있는 Email입니다."),
    ;


    ErrorCode(HttpStatus httpStatus, String errorCode, String message) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.message = message;
    }

    private HttpStatus httpStatus;
    private String errorCode;
    private String message;
}

