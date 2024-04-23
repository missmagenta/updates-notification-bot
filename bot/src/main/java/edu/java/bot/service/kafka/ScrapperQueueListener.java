package edu.java.bot.service.kafka;

import edu.java.bot.service.UpdateService;
import edu.java.dto.request.LinkUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

@Slf4j
@RequiredArgsConstructor
public class ScrapperQueueListener {
    private final UpdateService updateService;
    private final KafkaTemplate<String, LinkUpdateRequest> kafkaTemplate;

    @Value("${app.kafka-topic.topic-name}")
    private String topicName;

    @Value("${app.kafka-topic.dlq-suffix}")
    private String dlqSuffix;

    @KafkaListener(topics = "${app.kafka-topic.topic-name}")
    public void listen(LinkUpdateRequest linkUpdateRequest) {
        try {
            updateService.sendUpdate(linkUpdateRequest);
            log.info("Received message using KafkaListener and sent to bot: {}", linkUpdateRequest);
        } catch (Exception e) {
            log.error("Error processing message. Sending to Dead Letter Queue.", e);
            sendToDLQ(linkUpdateRequest);
        }
    }

    private void sendToDLQ(LinkUpdateRequest linkUpdateRequest) {
        String dlqTopic = topicName + dlqSuffix;
        kafkaTemplate.send(dlqTopic, linkUpdateRequest);
        log.info("Message sent to Dead Letter Queue: {}", linkUpdateRequest);
    }
}
