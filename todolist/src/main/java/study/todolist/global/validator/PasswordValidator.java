package study.todolist.global.validator;

import org.springframework.stereotype.Component;

@Component
public class PasswordValidator {
    private static final int MINIMUM_PASSWORD_LENGTH = 8;
    private static final String SPECIAL_CHARACTER_PATTERN = "^(?=.*[A-Z])(?=.*[!@#$%^&*()+=]).*$";

    public boolean validate(String password) {
        return isLengthValid(password) && containsSpecialCharacter(password);
    }

    private boolean isLengthValid(String password) {
        return password.length() >= MINIMUM_PASSWORD_LENGTH;
    }

    private boolean containsSpecialCharacter(String password) {
        return password.matches(SPECIAL_CHARACTER_PATTERN);
    }
}
