package net.thumbtack.school.notes.dto.validator;

import net.thumbtack.school.notes.serverexception.ServerErrorCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class LastNameValidator implements ConstraintValidator<LastName, String> {

    @Value("${max_name_length}")
    private int maxNameLength;

    @Override
    public void initialize(LastName constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isEmpty(s)) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(ServerErrorCode.NAME_IS_EMPTY.getErrorCode()).addConstraintViolation();
            return false;
        }
        if (s.length() > maxNameLength) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(ServerErrorCode.NAME_LENGTH_IS_OVERSIZE.getErrorCode()).addConstraintViolation();
            return false;
        }
        if (!s.matches("^[-a-zA-Zа-яА-ЯёЁ\\s]+$")) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(ServerErrorCode.NAME_IS_INVALID.getErrorCode()).addConstraintViolation();
            return false;
        }
        return true;
    }
}
