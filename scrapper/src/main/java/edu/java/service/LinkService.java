package edu.java.service;

import edu.java.dto.response.LinkResponse;
import edu.java.dto.response.ListLinkResponse;
import edu.java.model.Link;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

public interface LinkService {
    LinkResponse add(int tgChatId, String url);
    LinkResponse remove(int tgChatId, String url);
    ListLinkResponse listAll(int tgChatId);
    Link findLinkById(int id);
    ListLinkResponse findNotUpdatedForLongTime(long seconds);
    void update(int id, LocalDateTime lastUpdate);
}
