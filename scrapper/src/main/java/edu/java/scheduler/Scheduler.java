package edu.java.scheduler;

import java.time.Duration;

public record Scheduler(boolean enable, Duration interval, Duration forceCheckDelay) {
}
