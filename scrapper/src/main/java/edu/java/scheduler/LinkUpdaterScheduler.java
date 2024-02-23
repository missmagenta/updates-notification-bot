package edu.java.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LinkUpdaterScheduler {

    @Scheduled(fixedDelayString = "#{scheduler.interval().toMillis()}")
    public void update() {
        log.info("Going to db to check for updates");
    }
}
