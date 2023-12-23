package study.todolist.global.annotation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import study.todolist.domain.exception.PasswordValidateException;
import study.todolist.global.annotation.PasswordValidate;

public class PasswordPatternValidator implements ConstraintValidator<PasswordValidate, String> {

    private static final String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*[!@#$%^&*()+=]).*$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && isCorrectFormat(value);
    }

    private boolean isCorrectFormat(String value) {
        if (!value.matches(PASSWORD_PATTERN)) {
            throw new PasswordValidateException();
        }
        return true;
    }
}
