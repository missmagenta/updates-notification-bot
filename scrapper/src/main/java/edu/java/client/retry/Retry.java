package edu.java.client.retry;

import reactor.util.retry.RetrySpec;
import java.time.Duration;
import java.util.List;
import java.util.function.Predicate;


public final class Retry {
    private final int maxAttempts;
    private final BackoffStrategy strategy;
    private final Duration interval;
    private final Predicate<Throwable> statusFilterPredicate;

    public Retry(
        int maxAttempts,
        BackoffStrategy strategy,
        Duration interval,
        List<Integer> codes
    ) {
        this.maxAttempts = maxAttempts;
        this.strategy = strategy;
        this.interval = interval;
        this.statusFilterPredicate = ex -> !(ex instanceof StatusException) ||
            codes.contains(((StatusException) ex).getStatus().value());
    }

    public reactor.util.retry.Retry toReactorRetry() {
        return switch (strategy) {
            case CONSTANT -> RetrySpec.max(maxAttempts)
                .filter(statusFilterPredicate);
            case LINEAR -> RetrySpec.fixedDelay(maxAttempts, interval)
                .filter(statusFilterPredicate);
            case EXPONENTIAL -> RetrySpec.backoff(maxAttempts, interval)
                .filter(statusFilterPredicate);
        };
    }
}
