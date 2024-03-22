package edu.java.bot.handler;

import edu.java.bot.client.scrapper.ScrapperClient;
import edu.java.dto.response.LinkResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class TrackCommand implements Command {
    private final ScrapperClient scrapperClient;
    @Override
    public SendMessage apply(Update update) {
        long chatId = update.getMessage().getChatId();
        String link = update.getMessage().getText();

        ResponseEntity<LinkResponse> response = scrapperClient.addLinkToTrack((int) chatId, link);

        return SendMessage.builder()
            .chatId(chatId)
            .text(Objects.requireNonNull(response.getBody()).url())
            .build();
    }
}
