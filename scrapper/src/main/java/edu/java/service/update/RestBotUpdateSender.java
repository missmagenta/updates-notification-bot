package edu.java.service.update;

import edu.java.client.bot.BotClient;
import edu.java.dto.request.LinkUpdateRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestBotUpdateSender implements UpdateSender {
    private final BotClient botClient;

    @Override
    public void sendUpdate(LinkUpdateRequest linkUpdateRequest) {
        botClient.sendUpdate(
            linkUpdateRequest.id(),
            linkUpdateRequest.url(),
            linkUpdateRequest.description(),
            linkUpdateRequest.tgChatIds()
        );
    }
}
