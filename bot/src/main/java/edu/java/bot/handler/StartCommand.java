package edu.java.bot.handler;

import edu.java.bot.client.scrapper.ScrapperClient;
import edu.java.bot.utils.ConstantReplies;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class StartCommand implements Command {
    private final ScrapperClient scrapperClient;

    @Override
    public SendMessage apply(Update update) {
        long chatId = update.getMessage().getChatId();

        scrapperClient.registerChat((int) chatId);

        return SendMessage.builder()
            .chatId(String.valueOf(chatId))
            .text(ConstantReplies.START_MESSAGE)
            .build();
    }
}
