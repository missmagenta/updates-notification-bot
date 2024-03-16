package edu.java.service.jooq;

import edu.java.domain.dao.jooq.JooqChatDao;
import edu.java.service.TgChatService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JooqChatService implements TgChatService {
    private final JooqChatDao chatDao;

    @Override
    public void register(int tgChatId) {
        chatDao.add(tgChatId);
    }

    @Override
    public void unregister(int tgChatId) {
        chatDao.remove(tgChatId);
    }
}
