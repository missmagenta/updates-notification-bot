package edu.java.scheduler;

import edu.java.service.LinkUpdater;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class LinkUpdaterScheduler {
    private final LinkUpdater linkUpdater;

    @Scheduled(fixedDelayString = "#{scheduler.interval().toMillis()}")
    public void update() {
        log.info("Going to db to check for updates");
        linkUpdater.update();
    }
}
