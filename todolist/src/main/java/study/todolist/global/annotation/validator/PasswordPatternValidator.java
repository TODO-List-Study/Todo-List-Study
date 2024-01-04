package study.todolist.global.annotation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;
import study.todolist.global.annotation.PasswordValidate;

@Component
public class PasswordPatternValidator implements ConstraintValidator<PasswordValidate, String> {

    private static final String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*[!@#$%^&*()+=]).*$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && isCorrectFormat(value) && isCorrectRange(value.length());
    }

    private boolean isCorrectFormat(String value) {
        return value.matches(PASSWORD_PATTERN);
    }

    private boolean isCorrectRange(int passwordLength) {
        return passwordLength >= 8 && passwordLength <= 20;
    }
}
