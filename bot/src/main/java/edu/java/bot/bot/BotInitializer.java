package edu.java.bot.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
@Slf4j
public class BotInitializer {
    private final UpdatesNotificationBot updatesNotificationBot;

    @Autowired
    public BotInitializer(UpdatesNotificationBot updatesNotificationBot) {
        this.updatesNotificationBot = updatesNotificationBot;
    }

    @EventListener({ContextRefreshedEvent.class})
    public void init() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            telegramBotsApi.registerBot(updatesNotificationBot);
            log.info("Bot registered");
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }
}
