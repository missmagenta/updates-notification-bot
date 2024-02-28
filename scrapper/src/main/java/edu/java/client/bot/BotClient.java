package edu.java.client.bot;

import java.util.List;

public interface BotClient {
    void sendUpdate(
        int id,
        String url,
        String description,
        List<Integer> thChatIds);
}
