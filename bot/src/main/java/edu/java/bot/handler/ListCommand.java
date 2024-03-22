package edu.java.bot.handler;

import edu.java.bot.client.scrapper.ScrapperClient;
import edu.java.dto.response.ListLinkResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ListCommand implements Command {

    private final ScrapperClient scrapperClient;

    @Override
    public SendMessage apply(Update update) {
        long chatId = update.getMessage().getChatId();

        ResponseEntity<ListLinkResponse> links = scrapperClient.getTrackedLinks((int) chatId);

        return SendMessage.builder()
            .chatId(chatId)
            .text(Objects.requireNonNull(links.getBody()).linkResponses().toString())
            .build();
    }
}
