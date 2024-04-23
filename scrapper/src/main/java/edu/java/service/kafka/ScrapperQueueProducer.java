package edu.java.service.kafka;

import edu.java.configuration.ApplicationConfig;
import edu.java.configuration.kafka.info.KafkaTopic;
import edu.java.dto.request.LinkUpdateRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ScrapperQueueProducer {
    private final KafkaTemplate<String, LinkUpdateRequest> kafkaTemplate;
    private final KafkaTopic kafkaTopic;

    public ScrapperQueueProducer(
        KafkaTemplate<String, LinkUpdateRequest> kafkaTemplate,
        ApplicationConfig applicationConfig
        ) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaTopic = applicationConfig.kafkaTopic();
    }

    public void send(LinkUpdateRequest update) {
        kafkaTemplate.send(kafkaTopic.topicName(), update);
    }
}

