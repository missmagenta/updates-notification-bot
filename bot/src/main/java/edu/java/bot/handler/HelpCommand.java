package edu.java.bot.handler;

import edu.java.bot.handler.enums.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class HelpCommand implements edu.java.bot.handler.Command {
    @Override
    public SendMessage apply(Update update) {
        StringBuilder sb = new StringBuilder();
        sb.append("Available commands:\n");

        for (Command botCommand : Command.values()) {
            sb.append(botCommand.getValue()).append("\n");
        }

        long chatId = update.getMessage().getChatId();

        return SendMessage.builder()
            .chatId(String.valueOf(chatId))
            .text(sb.toString())
            .build();
    }
}
