package edu.java.service.update;

import edu.java.dto.request.LinkUpdateRequest;
import edu.java.service.kafka.ScrapperQueueProducer;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class KafkaBotUpdateSender implements UpdateSender {
    private final ScrapperQueueProducer scrapperQueueProducer;

    @Override
    public void sendUpdate(LinkUpdateRequest linkUpdateRequest) {
        scrapperQueueProducer.send(linkUpdateRequest);
    }
}
