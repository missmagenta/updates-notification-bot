package edu.java.bot.configuration;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
@PropertySource("classpath:application.yml")
public record ApplicationConfig(
    @NotEmpty
    String telegramToken,
    @NotEmpty
    String name
) {
}
