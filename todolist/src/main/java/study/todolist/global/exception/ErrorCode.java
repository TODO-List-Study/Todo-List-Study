package study.todolist.global.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    // 성공
    OK(HttpStatus.OK, "성공"),

    // 클라이언트 오류
    BAD_CREDENTIALS(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다."),
    INVALID_KEY_VALUE(HttpStatus.BAD_REQUEST, "키 값이 유효하지 않습니다."),
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "입력 값이 올바르지 않습니다."),
    TODO_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 ID의 할 일이 존재하지 않습니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 ID의 회원이 존재하지 않습니다."), // 추가: 회원 존재하지 않음

    // 서버 오류
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부 서버 오류");

    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
