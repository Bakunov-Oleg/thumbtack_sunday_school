package net.thumbtack.school.notes.dto.validator;

import net.thumbtack.school.notes.serverexception.ServerErrorCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class PasswordValidator implements ConstraintValidator<Password, String> {

    @Value("${min_password_length}")
    private int minPasswordLength;


    @Override
    public void initialize(Password constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isEmpty(s)) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(ServerErrorCode.PASSWORD_IS_EMPTY.getErrorCode()).addConstraintViolation();
            return false;
        }
        if (s.length() < minPasswordLength) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(ServerErrorCode.PASSWORD_LENGHT_IS_SHORT.getErrorCode()).addConstraintViolation();
            return false;
        }
        return true;
    }
}
