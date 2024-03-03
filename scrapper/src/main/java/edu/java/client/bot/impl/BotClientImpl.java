package edu.java.client.bot.impl;

import edu.java.client.bot.BotClient;
import edu.java.client.bot.BotRepositoryService;
import edu.java.dto.request.LinkUpdateRequest;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RequiredArgsConstructor
public class BotClientImpl implements BotClient {
    private final BotRepositoryService botRepositoryService;

    @Override
    public void sendUpdate(int id, String url, String description, List<Integer> thChatIds) {
        botRepositoryService.sendUpdate(new LinkUpdateRequest(id, url, description, thChatIds));
    }
}
