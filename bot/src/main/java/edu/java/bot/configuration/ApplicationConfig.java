package edu.java.bot.configuration;

import edu.java.bot.configuration.kafka.info.KafkaTopic;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
@PropertySource("classpath:application.yml")
public record ApplicationConfig(
    @NotEmpty String telegramToken,
    @NotEmpty String name,
    @NotNull KafkaTopic kafkaTopic,
    @NotNull String kafkaBootstrapAddress,
    @NotNull String kafkaGroupId,
    @NotNull String kafkaAutoOffsetReset
) {
}
