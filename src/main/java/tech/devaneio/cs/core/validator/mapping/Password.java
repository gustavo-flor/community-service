package tech.devaneio.cs.core.validator.mapping;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import tech.devaneio.cs.core.validator.PasswordValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface Password {

    String message() default "{tech.devaneio.cs.common.validator.mapping.Password.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    boolean atLeastOneUppercaseLetter() default true;

    boolean atLeastOneLowercaseLetter() default true;

    boolean atLeastOneNumber() default true;

    boolean atLeastOneSpecialChar() default true;

}
