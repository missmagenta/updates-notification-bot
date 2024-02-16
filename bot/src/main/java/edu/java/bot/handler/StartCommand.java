package edu.java.bot.handler;

import edu.java.bot.utils.ConstantReplies;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class StartCommand implements Command {
    @Override
    public SendMessage apply(Update update) {
        long chatId = update.getMessage().getChatId();

        return SendMessage.builder()
            .chatId(String.valueOf(chatId))
            .text(ConstantReplies.START_MESSAGE)
            .build();
    }
}
