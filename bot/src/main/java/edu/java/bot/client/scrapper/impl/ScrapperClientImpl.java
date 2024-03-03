package edu.java.bot.client.scrapper.impl;

import edu.java.bot.client.scrapper.ScrapperChatRepositoryService;
import edu.java.bot.client.scrapper.ScrapperClient;
import edu.java.bot.client.scrapper.ScrapperLinksRepositoryService;
import edu.java.dto.request.AddLinkRequest;
import edu.java.dto.request.RemoveLinkRequest;
import edu.java.dto.response.LinkResponse;
import edu.java.dto.response.ListLinkResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

@RequiredArgsConstructor
public class ScrapperClientImpl implements ScrapperClient {
    private final ScrapperChatRepositoryService scrapperChatRepositoryService;
    private final ScrapperLinksRepositoryService scrapperLinksRepositoryService;

    @Override
    public void registerChat(int chatId) {
        scrapperChatRepositoryService.registerChat(chatId);
    }

    @Override
    public void deleteChat(int chatId) {
        scrapperChatRepositoryService.deleteChat(chatId);
    }

    @Override
    public ResponseEntity<ListLinkResponse> getTrackedLinks(int chatId) {
        return scrapperLinksRepositoryService.getTrackedLinks(chatId);
    }

    @Override
    public ResponseEntity<LinkResponse> addLinkToTrack(int chatId, String link) {
        return scrapperLinksRepositoryService.addLinkToTrack(chatId, new AddLinkRequest(link));
    }

    @Override
    public ResponseEntity<LinkResponse> deleteLink(int chatId, String link) {
        return scrapperLinksRepositoryService.deleteLink(chatId, new RemoveLinkRequest(link));
    }
}
