package edu.java.bot.handler;

import edu.java.bot.handler.enums.CommandName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class HelpCommand implements Command {
    @Override
    public SendMessage apply(Update update) {
        StringBuilder sb = new StringBuilder();
        sb.append("Available commands:\n");

        for (CommandName botCommand : CommandName.values()) {
            sb.append(botCommand.getValue()).append("\n");
        }

        long chatId = update.getMessage().getChatId();

        return SendMessage.builder()
            .chatId(String.valueOf(chatId))
            .text(sb.toString())
            .build();
    }
}
