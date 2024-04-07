package edu.java.bot.configuration.kafka.info;

public record KafkaTopic(String topicName,
                         Integer partitionsCount,
                         Integer replicaCount) {
}
