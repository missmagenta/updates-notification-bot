package edu.java.service.jpa;

import edu.java.domain.jpa.JpaChatDao;
import edu.java.domain.jpa.JpaLinkDao;
import edu.java.model.Chat;
import edu.java.model.Link;
import edu.java.service.TgChatService;
import java.util.List;

public class JpaChatService implements TgChatService {

    private final JpaChatDao jpaChatDao;
    private final JpaLinkDao jpaLinkDao;

    public JpaChatService(JpaChatDao jpaChatDao, JpaLinkDao jpaLinkDao) {
        this.jpaChatDao = jpaChatDao;
        this.jpaLinkDao = jpaLinkDao;
    }

    @Override
    public void register(int tgChatId) {
        jpaChatDao.save(new Chat(tgChatId));
    }

    @Override
    public void unregister(int tgChatId) {
        Chat chat = jpaChatDao.findById(tgChatId).orElseThrow();
        List<Link> links = jpaLinkDao.findAll();
        links.forEach(link -> {
            if (link.getChats().contains(chat)) {
                link.deleteChat(chat);
            }
        });
        jpaChatDao.delete(chat);
    }
}
