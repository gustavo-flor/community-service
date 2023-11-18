package tech.devaneio.cs.core.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.text.Normalizer;
import java.util.Optional;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtil {

    private static final Pattern ONLY_DIGITS_PATTERN = Pattern.compile("\\d+");
    private static final Pattern NON_ASCII_PATTERN = Pattern.compile("[^\\p{ASCII}]");
    private static final String EMPTY = "";

    public static String removeNonDigits(final String input) {
        return Optional.ofNullable(input)
            .map(String::trim)
            .map(ONLY_DIGITS_PATTERN::matcher)
            .map(Matcher::results)
            .map(it -> it.map(MatchResult::group))
            .map(it -> it.collect(Collectors.joining()))
            .orElse(null);
    }

    public static String removeAccents(final String input) {
        return Optional.ofNullable(input)
            .map(it -> Normalizer.normalize(it, Normalizer.Form.NFKD))
            .map(NON_ASCII_PATTERN::matcher)
            .map(it -> it.replaceAll(EMPTY))
            .orElse(null);
    }

}
