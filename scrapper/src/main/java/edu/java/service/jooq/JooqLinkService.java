package edu.java.service.jooq;

import edu.java.domain.dao.ChatLinkDao;
import edu.java.domain.dao.LinkDao;
import edu.java.domain.dao.jooq.JooqChatDao;
import edu.java.domain.dao.jooq.JooqChatLinkDao;
import edu.java.domain.dao.jooq.JooqLinkDao;
import edu.java.dto.response.LinkResponse;
import edu.java.dto.response.ListLinkResponse;
import edu.java.model.ChatLink;
import edu.java.model.Link;
import edu.java.service.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessResourceFailureException;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class JooqLinkService implements LinkService {
    private final JooqLinkDao linkDao;
    private final JooqChatLinkDao chatLinkDao;
    private final JooqChatDao chatDao;

    @Override
    public LinkResponse add(int tgChatId, String url) {
        Integer linkId = linkDao.findIdByUrl(url);
        if (linkId == null) {
            linkDao.add(url);
        }
        chatDao.add(tgChatId);
        chatLinkDao.add(tgChatId, linkId);
        return new LinkResponse(linkId, url);
    }

    @Override
    public LinkResponse remove(int tgChatId, String url) {
        Integer linkId = linkDao.findIdByUrl(url);
        if (linkId != null) {
            chatLinkDao.remove(tgChatId, linkId);
        } else {
            throw new DataAccessResourceFailureException("No link with given id");
        }
        return new LinkResponse(linkId, url);
    }

    @Override
    public ListLinkResponse listAll(int tgChatId) {
        List<ChatLink> links = chatLinkDao.findAllLinksByChatId(tgChatId);
        List<LinkResponse> responses = links.stream().map(
            chatLink -> {
                String url = linkDao.findUrlById(chatLink.getLinkId());
                return new LinkResponse(chatLink.getLinkId(), url);
            })
            .toList();
        return new ListLinkResponse(responses, responses.size());
    }

    @Override
    public Link findLinkById(int id) {
        return linkDao.findById(id);
    }

    @Override
    public ListLinkResponse findNotUpdatedForLongTime(long seconds) {
        List<LinkResponse> responses = linkDao.findNotUpdatedForLongTime(seconds)
            .stream()
            .map(id -> {
                String url = linkDao.findUrlById(id);
                return new LinkResponse(id, url);
            })
            .toList();
        return new ListLinkResponse(responses, responses.size());
    }

    @Override
    public void update(int id, LocalDateTime lastUpdate) {
        linkDao.update(id, lastUpdate);
    }
}