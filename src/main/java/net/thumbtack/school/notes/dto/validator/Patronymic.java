package net.thumbtack.school.notes.dto.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PatronymicValidator.class)
public @interface Patronymic {
    String message() default "Incorrect patronymic!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
