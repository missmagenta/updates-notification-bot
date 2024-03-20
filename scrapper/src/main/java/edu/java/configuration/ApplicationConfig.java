package edu.java.configuration;

import edu.java.configuration.db.DatabaseInteraction;
import edu.java.scheduler.Scheduler;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
public record ApplicationConfig(
    @NotNull Scheduler scheduler,
    @NotNull DatabaseInteraction databaseInteraction,
    @NotNull String botApiUrl
) {
}
