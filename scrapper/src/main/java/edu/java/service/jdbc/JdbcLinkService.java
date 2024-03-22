package edu.java.service.jdbc;

import edu.java.domain.dao.ChatLinkDao;
import edu.java.domain.dao.LinkDao;
import edu.java.dto.response.LinkResponse;
import edu.java.dto.response.ListLinkResponse;
import edu.java.model.Link;
import edu.java.service.LinkService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.transaction.annotation.Transactional;

public class JdbcLinkService implements LinkService {
    private final LinkDao linkDao;
    private final ChatLinkDao chatLinkDao;

    public JdbcLinkService(
        @Qualifier("jdbcLinkDao") LinkDao linkDao,
        @Qualifier("jdbcChatLinkDao") ChatLinkDao chatLinkDao) {
        this.linkDao = linkDao;
        this.chatLinkDao = chatLinkDao;
    }

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
        List<Integer> linksIds = chatLinkDao.findAllLinksByChatId(tgChatId);
        List<LinkResponse> responses = linksIds
            .stream()
            .map(id -> {
                String url = linkDao.findUrlById(id);
                return new LinkResponse(id, url);
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
