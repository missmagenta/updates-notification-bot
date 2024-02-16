package edu.java.bot.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Command {
    START_COMMAND("/start", "Start using the bot"),
    HELP_COMMAND("/help", "Get a list of available commands"),
    TRACK_COMMAND("/track", "Start tracking service"),
    UNTRACK_COMMAND("/untrack", "Stop tracking service"),
    LIST_COMMAND("/list", "List all tracked services");

    private final String value;
    private final String description;
}

