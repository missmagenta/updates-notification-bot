package edu.java.configuration.kafka.info;

public record KafkaTopic(String topicName,
                         Integer partitionsCount,
                         Integer replicaCount) {
}
