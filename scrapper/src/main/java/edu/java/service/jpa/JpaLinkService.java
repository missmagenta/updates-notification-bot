package edu.java.service.jpa;

import edu.java.domain.jpa.JpaChatDao;
import edu.java.domain.jpa.JpaLinkDao;
import edu.java.dto.response.LinkResponse;
import edu.java.dto.response.ListLinkResponse;
import edu.java.model.Chat;
import edu.java.model.Link;
import edu.java.service.LinkService;
import java.time.LocalDateTime;
import java.util.List;

public class JpaLinkService implements LinkService {
    private final JpaLinkDao jpaLinkDao;
    private final JpaChatDao jpaChatDao;

    public JpaLinkService(JpaLinkDao jpaLinkDao, JpaChatDao jpaChatDao) {
        this.jpaLinkDao = jpaLinkDao;
        this.jpaChatDao = jpaChatDao;
    }

    @Override
    public LinkResponse add(int tgChatId, String url) {
        Chat chat = jpaChatDao.findById(tgChatId).orElseThrow();
        Link link = Link.builder()
            .name(url)
            .lastUpdateDate(LocalDateTime.now())
            .build();
        link.addChat(chat);

        link = jpaLinkDao.save(link);
        return new LinkResponse(link.getId(), link.getName());
    }

    @Override
    public LinkResponse remove(int tgChatId, String url) {
        Chat chat = jpaChatDao.findById(tgChatId).orElseThrow();
        Link link = jpaLinkDao.findLinkByChatsAndName(chat, url).orElseThrow();

        link.deleteChat(chat);
        jpaLinkDao.save(link);

        return new LinkResponse(link.getId(), link.getName());
    }

    @Override
    public ListLinkResponse listAll(int tgChatId) {
        Chat chat = jpaChatDao.findById(tgChatId).orElseThrow();
        List<Link> chatLinks = jpaLinkDao.findAllLinksByChats(chat);
        List<LinkResponse> responses = chatLinks
            .stream()
            .map(link -> new LinkResponse(link.getId(), link.getName()))
            .toList();

        return new ListLinkResponse(responses, responses.size());
    }

    @Override
    public Link findLinkById(int id) {
        return jpaLinkDao.findById(id).orElseThrow();
    }

    @Override
    public ListLinkResponse findNotUpdatedForLongTime(long seconds) {
        LocalDateTime threshold = LocalDateTime.now().minusSeconds(seconds);
        List<LinkResponse> responses = jpaLinkDao.findByLastUpdateDateBefore(threshold)
            .stream()
            .map(link -> new LinkResponse(link.getId(), link.getName()))
            .toList();
        return new ListLinkResponse(responses, responses.size());
    }

    @Override
    public void update(int id, LocalDateTime lastUpdate) {
        Link link = jpaLinkDao.findById(id).orElseThrow();
        link.setLastUpdateDate(lastUpdate);
        jpaLinkDao.save(link);
    }
}
