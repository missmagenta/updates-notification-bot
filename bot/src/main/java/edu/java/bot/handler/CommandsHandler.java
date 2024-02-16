package edu.java.bot.handler;

import edu.java.bot.enums.Command;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import edu.java.bot.utils.ConstantReplies;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import java.util.Map;

@Component
@Slf4j
public class CommandsHandler {
    private final Map<String, edu.java.bot.handler.Command> commands;

    public CommandsHandler(
        @Autowired StartCommand startCommand,
        @Autowired HelpCommand helpCommand,
        @Autowired TrackCommand trackCommand,
        @Autowired UntrackCommand untrackCommand,
        @Autowired ListCommand listCommand) {
        this.commands = Map.of(
            Command.START_COMMAND.getValue(), startCommand,
            Command.HELP_COMMAND.getValue(), helpCommand,
            Command.TRACK_COMMAND.getValue(), trackCommand,
            Command.UNTRACK_COMMAND.getValue(), untrackCommand,
            Command.LIST_COMMAND.getValue(), listCommand
        );
    }

    public SendMessage handleCommands(Update update) {
        String messageText = update.getMessage().getText();
        String command = messageText.split(" ")[0];
        long chatId = update.getMessage().getChatId();

        var commandHandler = commands.get(command);
        if (commandHandler != null) {
            return commandHandler.apply(update);
        } else {
            return new SendMessage(String.valueOf(chatId), ConstantReplies.UNKNOWN_COMMAND);
        }
    }

}
