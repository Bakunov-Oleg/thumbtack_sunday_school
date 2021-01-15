package net.thumbtack.school.notes.dto.validator;

import net.thumbtack.school.notes.serverexception.ServerErrorCode;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class DateValidator implements ConstraintValidator<Date, String> {

    private static final String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss";

    @Override
    public void initialize(Date constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            LocalDateTime.parse(s, DateTimeFormatter.ofPattern(DATE_FORMAT));
            return true;
        } catch (DateTimeParseException ex) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(ServerErrorCode.BAD_DATETIME.getErrorCode()).addConstraintViolation();
            return false;
        }
    }
}
