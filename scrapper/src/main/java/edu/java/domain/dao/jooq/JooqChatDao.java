package edu.java.domain.dao.jooq;

import edu.java.domain.dao.ChatDao;
import edu.java.model.Chat;
import edu.java.scrapper.domain.jooq.Tables;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.exception.DataAccessException;
import org.springframework.transaction.annotation.Transactional;
import static edu.java.scrapper.domain.jooq.Tables.CHAT;

@RequiredArgsConstructor
@Slf4j
public class JooqChatDao implements ChatDao {
    private final DSLContext dslContext;

    @Override
    @Transactional
    public Boolean add(int id) {
        try {
            dslContext.insertInto(CHAT).set(CHAT.ID, id).execute();
            return Boolean.TRUE;
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            return Boolean.FALSE;
        }
    }

    @Override
    @Transactional
    public Boolean remove(int id) {
        try {
            int deleted = dslContext.deleteFrom(CHAT).where(CHAT.ID.eq(id)).execute();
            if (deleted == 1) {
                dslContext.deleteFrom(Tables.CHAT_LINK).where(Tables.CHAT_LINK.CHAT_ID.eq(id)).execute();
            }
            return Boolean.TRUE;
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            return Boolean.FALSE;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Chat> findAll() {
        return dslContext
            .selectFrom(CHAT)
            .fetchStream()
            .map(alias -> new Chat(alias.get(CHAT.ID)))
            .toList();
    }
}
