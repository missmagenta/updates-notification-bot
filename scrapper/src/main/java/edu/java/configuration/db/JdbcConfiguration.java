package edu.java.configuration.db;

import edu.java.domain.dao.jdbc.JdbcChatDao;
import edu.java.domain.dao.jdbc.JdbcChatLinkDao;
import edu.java.domain.dao.jdbc.JdbcLinkDao;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@RequiredArgsConstructor
public class JdbcConfiguration {
    private final JdbcTemplate jdbcTemplate;

    @Bean
    JdbcLinkDao jdbcLinkDao() {
        return new JdbcLinkDao(jdbcTemplate);
    }

    @Bean
    JdbcChatDao jdbcChatDao() {
        return new JdbcChatDao(jdbcTemplate);
    }

    @Bean
    JdbcChatLinkDao jdbcChatLinkDao() {
        return new JdbcChatLinkDao(jdbcTemplate);
    }
}
