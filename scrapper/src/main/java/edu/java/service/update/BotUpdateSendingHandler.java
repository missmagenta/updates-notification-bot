package edu.java.service.update;

import edu.java.client.bot.BotClient;
import edu.java.dto.request.LinkUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BotUpdateSendingHandler {
    private final BotClient botClient;

    public void handleSendingUpdates(LinkUpdateRequest linkUpdateRequest) {
        botClient.sendUpdate(
            linkUpdateRequest.id(),
            linkUpdateRequest.url(),
            linkUpdateRequest.description(),
            linkUpdateRequest.tgChatIds()
        );
    }
}
