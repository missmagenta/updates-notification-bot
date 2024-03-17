package edu.java.configuration.db;

import edu.java.domain.dao.jdbc.JdbcChatDao;
import edu.java.domain.dao.jdbc.JdbcChatLinkDao;
import edu.java.domain.dao.jdbc.JdbcLinkDao;
import edu.java.domain.dao.jooq.JooqChatDao;
import edu.java.domain.dao.jooq.JooqChatLinkDao;
import edu.java.domain.dao.jooq.JooqLinkDao;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@RequiredArgsConstructor
public class JooqConfiguration {
    private final DSLContext dslContext;

    @Bean
    JooqLinkDao jdbcLinkDao() {
        return new JooqLinkDao(dslContext);
    }

    @Bean
    JooqChatDao jdbcChatDao() {
        return new JooqChatDao(dslContext);
    }

    @Bean
    JooqChatLinkDao jdbcChatLinkDao() {
        return new JooqChatLinkDao(dslContext);
    }
}
