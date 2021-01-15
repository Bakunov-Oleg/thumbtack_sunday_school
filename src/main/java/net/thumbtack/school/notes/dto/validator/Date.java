package net.thumbtack.school.notes.dto.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateValidator.class)
public @interface Date {
    String message() default "Incorrect date or time";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
