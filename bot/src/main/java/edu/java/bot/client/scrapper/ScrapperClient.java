package edu.java.bot.client.scrapper;

import edu.java.dto.response.LinkResponse;
import edu.java.dto.response.ListLinkResponse;
import org.springframework.http.ResponseEntity;

public interface ScrapperClient {
    void registerChat(int chatId);
    void deleteChat(int chatId);
    ResponseEntity<ListLinkResponse> getTrackedLinks(int chatId);
    ResponseEntity<LinkResponse> addLinkToTrack(int chatId, String link);
    ResponseEntity<LinkResponse> deleteLink(int chatId, String link);
}
