package edu.java.client.bot;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public interface BotClient {
    void sendUpdate(
        @NotNull int id,
        @NotNull String url,
        @NotNull String description,
        @NotNull List<Integer> tgChatIds);
}
