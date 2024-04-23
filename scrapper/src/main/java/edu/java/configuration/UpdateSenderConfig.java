package edu.java.configuration;

import edu.java.client.bot.BotClient;
import edu.java.service.kafka.ScrapperQueueProducer;
import edu.java.service.update.KafkaBotUpdateSender;
import edu.java.service.update.RestBotUpdateSender;
import edu.java.service.update.UpdateSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdateSenderConfig {
    private final boolean useQueue;

    public UpdateSenderConfig(ApplicationConfig applicationConfig) {
        this.useQueue = applicationConfig.useQueue();
    }

    @Bean
    public UpdateSender updateSender(BotClient botClient, ScrapperQueueProducer scrapperQueueProducer) {
        if (useQueue) {
            return new KafkaBotUpdateSender(scrapperQueueProducer);
        } else {
            return new RestBotUpdateSender(botClient);
        }
    }
}
