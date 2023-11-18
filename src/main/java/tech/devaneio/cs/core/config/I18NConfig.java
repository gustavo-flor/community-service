package tech.devaneio.cs.core.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;

@Configuration
public class I18NConfig {

    private static final String DEFAULT_ENCODING = StandardCharsets.UTF_8.name();
    private static final Locale DEFAULT_LOCALE = Locale.US;
    private static final List<Locale> SUPPORTED_LOCALES = List.of(DEFAULT_LOCALE);
    private static final String MESSAGES_BASENAME = "classpath:i18n/ValidationMessages";

    @Bean
    public LocaleResolver localeResolver() {
        final var localeResolver = new AcceptHeaderLocaleResolver();
        localeResolver.setDefaultLocale(DEFAULT_LOCALE);
        localeResolver.setSupportedLocales(SUPPORTED_LOCALES);
        return localeResolver;
    }

    @Bean
    public MessageSource messageSource() {
        final var messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(MESSAGES_BASENAME);
        messageSource.setDefaultEncoding(DEFAULT_ENCODING);
        messageSource.setDefaultLocale(DEFAULT_LOCALE);
        return messageSource;
    }

}
