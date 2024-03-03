package edu.java.bot.client.scrapper;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.PostExchange;

public interface ScrapperChatRepositoryService {
    @PostExchange("/tg-chat/{id}")
    void registerChat(@PathVariable("id") int id);

    @DeleteExchange ("/tg-chat-id/{id}")
    void deleteChat(@PathVariable("id") int id);
}
