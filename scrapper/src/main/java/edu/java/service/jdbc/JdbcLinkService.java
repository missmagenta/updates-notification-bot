package edu.java.service.jdbc;

import edu.java.domain.dao.ChatLinkDao;
import edu.java.domain.dao.LinkDao;
import edu.java.domain.dao.jdbc.JdbcChatLinkDao;
import edu.java.domain.dao.jdbc.JdbcLinkDao;
import edu.java.dto.response.LinkResponse;
import edu.java.dto.response.ListLinkResponse;
import edu.java.model.ChatLink;
import edu.java.model.Link;
import edu.java.service.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JdbcLinkService implements LinkService {
    private final JdbcLinkDao linkDao;
    private final JdbcChatLinkDao chatLinkDao;

    @Override
    public LinkResponse add(int tgChatId, String url) {
        Integer linkId = linkDao.findIdByUrl(url);
        if (linkId == null) {
            linkDao.add(url);
        } else {
            chatLinkDao.add(tgChatId, linkId);
        }
        return new LinkResponse(tgChatId, url);
    }

    @Override
    public LinkResponse remove(int tgChatId, String url) {
        Integer linkId = linkDao.findIdByUrl(url);
        if (linkId != null) {
            chatLinkDao.remove(tgChatId, linkId);
            linkDao.remove(tgChatId);
        } else {
            throw new DataAccessResourceFailureException("No link with given id");
        }
        return new LinkResponse(linkId, url);
    }

    @Override
    public ListLinkResponse listAll(int tgChatId) {
        List<ChatLink> links = chatLinkDao.findAllLinksByChatId(tgChatId);
        List<LinkResponse> responses = links
            .stream()
            .map(chatLink -> {
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
    @Transactional(readOnly = true)
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