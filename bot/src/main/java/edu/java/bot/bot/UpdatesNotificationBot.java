package edu.java.bot.bot;

import edu.java.bot.configuration.ApplicationConfig;
import edu.java.bot.handler.enums.CommandName;
import edu.java.bot.utils.ConstantReplies;
import edu.java.bot.handler.CommandsHandler;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.Arrays;

@Component
@Slf4j
public class UpdatesNotificationBot extends TelegramLongPollingBot {
    @NotNull
    public final ApplicationConfig applicationConfig;
    @NotNull
    public final CommandsHandler commandsHandler;

    public UpdatesNotificationBot(ApplicationConfig applicationConfig, CommandsHandler commandsHandler) {
        super(applicationConfig.telegramToken());
        this.applicationConfig = applicationConfig;
        this.commandsHandler = commandsHandler;

        addCommandsToMenu();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String chatId = update.getMessage().getChatId().toString();
            if (update.getMessage().getText().startsWith("/")) {
                log.info("Received command: " + update.getMessage().getText());
                sendMessage(commandsHandler.handleCommands(update));
            } else {
                sendMessage(new SendMessage(chatId, ConstantReplies.CANNOT_UNDERSTAND));
            }
        }
    }

    private void sendMessage(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public String getBotUsername() {
        return applicationConfig.name();
    }

    public void addCommandsToMenu() {
        SetMyCommands setMyCommands = new SetMyCommands();
        setMyCommands.setCommands(Arrays.stream(CommandName.values()).
            map(command -> new BotCommand(command.getValue(), command.getDescription())).
            toList());
        try {
            execute(setMyCommands);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }
}
