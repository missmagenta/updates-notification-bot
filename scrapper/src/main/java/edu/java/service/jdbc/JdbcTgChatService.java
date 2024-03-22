package edu.java.service.jdbc;

import edu.java.domain.dao.ChatDao;
import edu.java.domain.dao.ChatLinkDao;
import edu.java.service.TgChatService;
import org.springframework.beans.factory.annotation.Qualifier;

public class JdbcTgChatService implements TgChatService {
    private final ChatDao chatDao;
    private final ChatLinkDao chatLinkDao;

    public JdbcTgChatService(
        @Qualifier("jdbcChatDao") ChatDao chatDao,
        @Qualifier("jdbcChatLinkDao") ChatLinkDao chatLinkDao
    ) {
        this.chatDao = chatDao;
        this.chatLinkDao = chatLinkDao;
    }

    @Override
    public void register(int tgChatId) {
        chatDao.add(tgChatId);
    }

    @Override
    public void unregister(int tgChatId) {
        chatLinkDao.removeByChatId(tgChatId);
        chatDao.remove(tgChatId);
    }
}
