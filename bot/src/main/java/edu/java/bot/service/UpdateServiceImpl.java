package edu.java.bot.service;

import edu.java.bot.bot.UpdatesNotificationBot;
import edu.java.dto.request.LinkUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateServiceImpl implements UpdateService {
    private final UpdatesNotificationBot updatesNotificationBot;

    @Override
    public void sendUpdate(LinkUpdateRequest linkUpdateRequest) {
        linkUpdateRequest.tgChatIds().forEach(chatId -> {
            try {
                updatesNotificationBot.execute(SendMessage
                    .builder()
                    .chatId(Long.valueOf(chatId))
                    .text(linkUpdateRequest.description())
                    .build());
            } catch (TelegramApiException e) {
                log.error("Failed to send updates to chatId {}", chatId);
            }
        });

    }
}
