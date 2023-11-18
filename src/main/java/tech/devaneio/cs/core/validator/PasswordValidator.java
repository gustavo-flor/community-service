package tech.devaneio.cs.core.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;
import tech.devaneio.cs.core.validator.mapping.Password;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.text.MessageFormat.format;
import static java.util.Objects.requireNonNull;

@Component
public class PasswordValidator implements ConstraintValidator<Password, String> {

    private static final String VALID_SPECIAL_CHARS_MATCHER = "@$!%*?&";
    private static final String VALID_CHARS_MATCHER = format("A-Za-z\\d{0}", VALID_SPECIAL_CHARS_MATCHER);
    private static final String AT_LEAST_ONE_LOWERCASE_LETTER_MATCHER = "(?=.*[a-z])";
    private static final String AT_LEAST_ONE_UPPERCASE_LETTER_MATCHER = "(?=.*[A-Z])";
    private static final String AT_LEAST_ONE_NUMBER_MATCHER = "(?=.*\\d)";
    private static final String AT_LEAST_ONE_SPECIAL_CHAR_MATCHER = format("(?=.*[{0}])", VALID_SPECIAL_CHARS_MATCHER);
    private static final String PATTERN_TEMPLATE = "^{0}{1}{2}{3}{4}$";

    private Pattern passwordPattern;

    @Override
    public void initialize(final Password annotation) {
        final var regexp = format(
            PATTERN_TEMPLATE,
            annotation.atLeastOneLowercaseLetter() ? AT_LEAST_ONE_LOWERCASE_LETTER_MATCHER : "",
            annotation.atLeastOneUppercaseLetter() ? AT_LEAST_ONE_UPPERCASE_LETTER_MATCHER : "",
            annotation.atLeastOneNumber() ? AT_LEAST_ONE_NUMBER_MATCHER : "",
            annotation.atLeastOneSpecialChar() ? AT_LEAST_ONE_SPECIAL_CHAR_MATCHER : "",
            format("[{0}]{1}", VALID_CHARS_MATCHER, "{8,}")
        );
        passwordPattern = Pattern.compile(regexp);
    }

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        requireNonNull(passwordPattern, "Password pattern is null, but it's required to execute is valid");
        return Optional.ofNullable(value)
            .map(it -> passwordPattern.matcher(it))
            .map(Matcher::matches)
            .orElse(true);
    }

}
