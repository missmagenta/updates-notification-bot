package edu.java.client.bot;

import edu.java.dto.request.LinkUpdateRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

public interface BotRepositoryService {
    @PostExchange("/updates")
    void sendUpdate(@RequestBody LinkUpdateRequest linkUpdateRequest);
}
