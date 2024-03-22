package edu.java.configuration.db;

import edu.java.domain.dao.jdbc.JdbcChatDao;
import edu.java.domain.dao.jdbc.JdbcChatLinkDao;
import edu.java.domain.dao.jdbc.JdbcLinkDao;
import edu.java.service.jdbc.JdbcLinkService;
import edu.java.service.jdbc.JdbcTgChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "access-type", havingValue = "jdbc")
@RequiredArgsConstructor
public class JdbcConfiguration {
    private final JdbcTemplate jdbcTemplate;

    @Bean("jdbcLinkDao")
    public JdbcLinkDao jdbcLinkDao() {
        return new JdbcLinkDao(jdbcTemplate);
    }

    @Bean("jdbcChatDao")
    public JdbcChatDao jdbcChatDao() {
        return new JdbcChatDao(jdbcTemplate);
    }

    @Bean("jdbcChatLinkDao")
    public JdbcChatLinkDao jdbcChatLinkDao() {
        return new JdbcChatLinkDao(jdbcTemplate);
    }

    @Bean("jdbcTgChatService")
    public JdbcTgChatService jdbcTgChatService() {
        return new JdbcTgChatService(jdbcChatDao(), jdbcChatLinkDao());
    }

    @Bean("jdbcLinkService")
    public JdbcLinkService jdbcLinkService() {
        return new JdbcLinkService(jdbcLinkDao(), jdbcChatLinkDao());
    }
}
