package edu.java.service.update;

import edu.java.client.bot.BotClient;
import edu.java.dto.request.LinkUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RestBotUpdateSender {
    private final BotClient botClient;

    public void sendUpdates(LinkUpdateRequest linkUpdateRequest) {
        botClient.sendUpdate(
            linkUpdateRequest.id(),
            linkUpdateRequest.url(),
            linkUpdateRequest.description(),
            linkUpdateRequest.tgChatIds()
        );
    }
}
