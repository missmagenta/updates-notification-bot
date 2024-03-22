package edu.java.domain.dao.jdbc;

import edu.java.domain.dao.ChatLinkDao;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class JdbcChatLinkDao implements ChatLinkDao {
    private final JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public Boolean add(int chatId, int linkId) {
        String sql = "INSERT INTO chat_link (chat_id, link_id, link_started_track_date, link_untrack_date) " +
            "VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(
            sql,
            chatId,
            linkId,
            Timestamp.valueOf(LocalDateTime.now()),
            null
        ) > 0;
    }

    @Override
    @Transactional
    public Boolean remove(int chatId, int linkId) {
        String sql = "DELETE FROM chat_link WHERE chat_id = ? AND link_id = ?";
        return jdbcTemplate.update(sql, chatId, linkId) > 0;
    }

    @Override
    public Boolean removeByChatId(int chatId) {
        String sql = "DELETE FROM chat_link WHERE chat_id = ?";
        return jdbcTemplate.update(sql, chatId) > 0;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Integer> findAllLinksByChatId(int chatId) {
        return jdbcTemplate.query("SELECT * FROM chat_link WHERE chat_link.chat_id = ?",
            (rs, rowNum) -> rs.getInt("link_id"), chatId);
    }
}
