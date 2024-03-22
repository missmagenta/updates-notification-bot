package edu.java.domain.dao.jooq;

import edu.java.domain.dao.LinkDao;
import edu.java.dto.response.LinkResponse;
import edu.java.model.Chat;
import edu.java.model.Link;
import edu.java.scrapper.domain.jooq.Tables;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.exception.DataAccessException;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import static edu.java.scrapper.domain.jooq.Tables.LINK;

@RequiredArgsConstructor
@Slf4j
public class JooqLinkDao implements LinkDao {
    private final DSLContext dslContext;

    @Override
    @Transactional
    public Integer add(String url) {
        return Objects.requireNonNull(dslContext.insertInto(LINK)
                .set(LINK.NAME, url)
                .set(LINK.LAST_UPDATE_DATE, LocalDateTime.now())
                .returningResult(LINK.ID)
                .fetchOne())
            .into(Integer.class);
    }

    @Override
    @Transactional
    public Boolean remove(int id) {
        try {
            dslContext.deleteFrom(LINK).where(LINK.ID.eq(id));
            return Boolean.TRUE;
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            return Boolean.FALSE;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<LinkResponse> findAll() {
        return dslContext
            .selectFrom(LINK)
            .fetchStream()
            .map(alias -> new LinkResponse(alias.get(LINK.ID), alias.get(LINK.NAME)))
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Integer findIdByUrl(String url) {
        return Objects.requireNonNull(dslContext
                .select(LINK.ID)
                .from(LINK)
                .where(LINK.NAME.eq(url))
                .fetchOne())
            .into(Integer.class);
    }

    @Override
    @Transactional(readOnly = true)
    public String findUrlById(int id) {
        return dslContext
            .select(LINK.NAME)
            .from(LINK)
            .where(LINK.ID.eq(id))
            .fetchOne()
            .into(String.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Link findById(int id) {
        return dslContext
            .selectFrom(LINK)
            .where(LINK.ID.eq(id))
            .fetch()
            .map(alias -> new Link(id, alias.getName(), alias.getLastupdatedate(), findChatsByLinkId(id)))
            .get(0);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Integer> findNotUpdatedForLongTime(long seconds) {
        LocalDateTime timePeriod = LocalDateTime.now().minusSeconds(seconds);
        return dslContext.select(LINK.ID)
            .from(LINK)
            .where(LINK.LAST_UPDATE_DATE.lessThan(timePeriod))
            .fetchStream()
            .map(alias -> alias.get(LINK.ID))
            .toList();
    }

    @Override
    public void update(int id, LocalDateTime lastUpdate) {
        dslContext.update(LINK)
            .set(LINK.LAST_UPDATE_DATE, lastUpdate)
            .where(LINK.ID.eq(id))
            .execute();
    }

    private List<Chat> findChatsByLinkId(int id) {
        return dslContext.select(Tables.CHAT_LINK.CHAT_ID)
            .from(Tables.CHAT_LINK)
            .where(Tables.CHAT_LINK.LINK_ID.eq(id))
            .fetchStream()
            .map(alias -> new Chat(alias.get(Tables.CHAT_LINK.CHAT_ID)))
            .toList();
    }
}
