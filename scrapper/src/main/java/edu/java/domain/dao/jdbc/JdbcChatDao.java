package edu.java.domain.dao.jdbc;

import edu.java.domain.dao.ChatDao;
import edu.java.model.Chat;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class JdbcChatDao implements ChatDao {

    private final JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public Boolean add(@NotNull int id) {
        String sql = "INSERT INTO chat (id) values (?)";
        return jdbcTemplate.update(sql, id) > 0;
    }

    @Override
    @Transactional
    public Boolean remove(@NotNull int id) {
        if (Integer.valueOf(1)
            .equals(jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM chat_link WHERE chat_id = ?", Integer.class, id))) {
            String sqlToChatLinkTable = "DELETE FROM chat_link WHERE chat_id = ?";
            jdbcTemplate.update(sqlToChatLinkTable, id);
        }
        if (
            Integer.valueOf(1)
                .equals(jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM chat WHERE id = ?", Integer.class, id))
        ) {
            String sqlToChatTable = "DELETE FROM chat where id = ?";
            return (jdbcTemplate.update(sqlToChatTable, id) > 0);
        } else {
            return Boolean.FALSE;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<@NotNull Chat> findAll() {
        String sql = "SELECT * FROM chat";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Chat(rs.getInt("id")));
    }
}
