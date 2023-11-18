package tech.devaneio.cs.core.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;
import tech.devaneio.cs.core.util.StringUtil;
import tech.devaneio.cs.core.validator.mapping.FullName;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.requireNonNull;

@Component
public class FullNameValidator implements ConstraintValidator<FullName, String> {

    private Pattern fullNamePattern;

    @Override
    public void initialize(final FullName constraintAnnotation) {
        fullNamePattern = Pattern.compile(constraintAnnotation.regexp());
    }

    @Override
    public boolean isValid(final String input, final ConstraintValidatorContext context) {
        requireNonNull(fullNamePattern, "Full name pattern is null, but it's required to execute is valid");
        return Optional.ofNullable(input)
            .map(String::trim)
            .map(StringUtil::removeAccents)
            .map(it -> fullNamePattern.matcher(it))
            .map(Matcher::matches)
            .orElse(true);
    }

}
