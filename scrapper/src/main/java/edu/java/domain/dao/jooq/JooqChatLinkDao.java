package edu.java.domain.dao.jooq;

import edu.java.domain.dao.ChatLinkDao;
import edu.java.model.ChatLink;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.exception.DataAccessException;
import org.springframework.transaction.annotation.Transactional;
import static edu.java.scrapper.domain.jooq.Tables.CHAT_LINK;

@RequiredArgsConstructor
@Slf4j
public class JooqChatLinkDao implements ChatLinkDao {
    private final DSLContext dslContext;

    @Override
    @Transactional
    public Boolean add(int chatId, int linkId) {
        try {
            dslContext.insertInto(CHAT_LINK)
                .set(CHAT_LINK.CHAT_ID, chatId)
                .set(CHAT_LINK.LINK_ID, linkId)
                .execute();
            return Boolean.TRUE;
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            return Boolean.FALSE;
        }
    }

    @Override
    @Transactional
    public Boolean remove(int chatId, int linkId) {
        try {
            dslContext
                .deleteFrom(CHAT_LINK)
                .where(CHAT_LINK.CHAT_ID.eq(chatId).and(CHAT_LINK.LINK_ID.eq(linkId)))
                .execute();
            return Boolean.TRUE;
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            return Boolean.FALSE;
        }
    }

    @Override
    @Transactional
    public Boolean removeByChatId(int chatId) {
        try {
            dslContext
                .deleteFrom(CHAT_LINK)
                .where(CHAT_LINK.CHAT_ID.eq(chatId))
                .execute();
            return Boolean.TRUE;
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            return Boolean.FALSE;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChatLink> findAllLinksByChatId(int chatId) {
        return dslContext
            .selectFrom(CHAT_LINK)
            .where(CHAT_LINK.CHAT_ID.eq(chatId))
            .fetchStream()
            .map(alias -> new ChatLink(
                chatId, alias.get(CHAT_LINK.LINK_ID), alias.getLinkStartedTrackDate(), alias.getLinkUntrackDate()))
            .toList();
    }
}
