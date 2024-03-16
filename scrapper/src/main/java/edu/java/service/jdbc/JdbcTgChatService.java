package edu.java.service.jdbc;

import edu.java.domain.dao.ChatDao;
import edu.java.domain.dao.ChatLinkDao;
import edu.java.service.TgChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JdbcTgChatService implements TgChatService {
    private final ChatDao chatDao;
    private final ChatLinkDao chatLinkDao;

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
