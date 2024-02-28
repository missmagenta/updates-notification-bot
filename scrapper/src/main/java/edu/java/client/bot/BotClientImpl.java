package edu.java.client.bot;

import edu.java.request.LinkUpdateRequest;
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
