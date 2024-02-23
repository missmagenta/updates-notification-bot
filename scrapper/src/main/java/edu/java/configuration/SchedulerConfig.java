package edu.java.configuration;

import edu.java.scheduler.Scheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfig {
    private final ApplicationConfig applicationConfig;

    public SchedulerConfig(ApplicationConfig applicationConfig) {
        this.applicationConfig = applicationConfig;
    }

    @Bean
    public Scheduler scheduler() {
        return applicationConfig.getScheduler();
    }
}
