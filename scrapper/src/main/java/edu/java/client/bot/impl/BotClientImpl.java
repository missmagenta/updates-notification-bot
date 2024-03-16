package edu.java.client.bot.impl;

import edu.java.client.bot.BotClient;
import edu.java.client.bot.BotRepositoryService;
import edu.java.dto.request.LinkUpdateRequest;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RequiredArgsConstructor
public class BotClientImpl implements BotClient {
    private final BotRepositoryService botRepositoryService;

    @Override
    public void sendUpdate(
        @NotNull int id,
        @NotNull String url,
        @NotNull String description,
        @NotNull List<Integer> tgChatIds) {
        botRepositoryService.sendUpdate(new LinkUpdateRequest(id, url, description, tgChatIds));
    }
}
