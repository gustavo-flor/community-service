package tech.devaneio.cs.core.validator.mapping;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import tech.devaneio.cs.core.validator.FullNameValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FullNameValidator.class)
public @interface FullName {

    String message() default "{tech.devaneio.cs.common.validator.mapping.FullName.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String regexp() default "^[a-zA-Z]{2,}(?: [a-zA-Z]+)+$";

}
