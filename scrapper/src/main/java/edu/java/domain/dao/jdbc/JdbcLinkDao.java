package edu.java.domain.dao.jdbc;

import edu.java.domain.dao.LinkDao;
import edu.java.dto.response.LinkResponse;
import edu.java.model.Chat;
import edu.java.model.Link;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class JdbcLinkDao implements LinkDao {
    private final JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public Integer add(String url) {
        String sql = "INSERT INTO link (name, last_update_date) VALUES (?, ?) RETURNING id";
        return jdbcTemplate.update(sql,
            Long.class, url, Timestamp.valueOf(LocalDateTime.now())
        );
    }

    @Override
    @Transactional
    public Boolean remove(int id) {
        String sql = "DELETE FROM link where id = ?";
        return jdbcTemplate.update(sql, id) > 0;
    }

    @Override
    @Transactional(readOnly = true)
    public List<LinkResponse> findAll() {
        String sql = "SELECT * FROM link";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new LinkResponse(
            rs.getInt("id"),
            rs.getString("name")
        ));
    }

    @Override
    @Transactional(readOnly = true)
    public Integer findIdByUrl(String url) {
        String sql = "SELECT id FROM link WHERE link.name = ?";
        return jdbcTemplate.query(sql,
            (rs, rowNum) -> rs.getInt("id"), url
        ).get(0);
    }

    @Override
    public String findUrlById(int id) {
        String sql = "SELECT name FROM link WHERE link.id = ?";
        return jdbcTemplate.query(sql,
            (rs, rowNum) -> rs.getString("name"), id
        ).get(0);
    }

    @Override
    public Link findById(int id) {
        String sql = "SELECT * FROM link WHERE link.id = ?";
        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> new Link(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getTimestamp("last_update_date").toLocalDateTime(),
                    findChatsByLinkId(id)
                ),
                id
            )
            .get(0);
    }

    @Override
    @Transactional
    public List<Integer> findNotUpdatedForLongTime(long seconds) {
        LocalDateTime timePeriod = LocalDateTime.now().minusSeconds(seconds);
        return jdbcTemplate.query(
            "SELECT * FROM link WHERE last_updated <= ?",
            (rs, rowNum) -> rs.getInt("id"),
            Timestamp.valueOf(timePeriod)
        );
    }

    @Override
    @Transactional
    public void update(int id, LocalDateTime lastUpdate) {
        String sql = "UPDATE link SET last_updated_date = ? where link.id = ?";
        jdbcTemplate.update(sql, Timestamp.valueOf(lastUpdate), id);
    }

    private List<Chat> findChatsByLinkId(int id) {
        String sql = "SELECT chat_id FROM chat_link WHERE chat_link.link_id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Chat(rs.getInt("chat_id")), id);
    }
}
