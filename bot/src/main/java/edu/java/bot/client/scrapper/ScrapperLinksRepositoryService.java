package edu.java.bot.client.scrapper;

import edu.java.dto.request.AddLinkRequest;
import edu.java.dto.request.RemoveLinkRequest;
import edu.java.dto.response.LinkResponse;
import edu.java.dto.response.ListLinkResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

public interface ScrapperLinksRepositoryService {
    @GetExchange("/links")
    ResponseEntity<ListLinkResponse> getTrackedLinks(int chatId);

    @PostExchange("/links")
    ResponseEntity<LinkResponse> addLinkToTrack(int chatId, AddLinkRequest addLinkRequest);

    @DeleteExchange("/links")
    ResponseEntity<LinkResponse> deleteLink(int chatId, RemoveLinkRequest removeLinkRequest);
}
