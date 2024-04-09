package edu.java.configuration;

import edu.java.client.retry.BackoffStrategy;
import edu.java.client.retry.Retry;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;
import java.time.Duration;
import java.util.List;

@Validated
@Getter
@Setter
@ConfigurationProperties(prefix = "retry", ignoreUnknownFields = false)
public class RetryConfig {
    private int maxAttempts;
    private String strategy;
    private Duration interval;
    private List<Integer> codes;

    @Bean
    public Retry retry() {
        BackoffStrategy backoffStrategy = BackoffStrategy.valueOf(strategy.toUpperCase());
        return new Retry(maxAttempts, backoffStrategy, interval, codes);
    }
}
