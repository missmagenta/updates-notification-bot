package edu.java.configuration.db;

import edu.java.domain.dao.jooq.JooqChatDao;
import edu.java.domain.dao.jooq.JooqChatLinkDao;
import edu.java.domain.dao.jooq.JooqLinkDao;
import edu.java.service.jooq.JooqChatService;
import edu.java.service.jooq.JooqLinkService;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jooq")
@RequiredArgsConstructor
public class JooqConfiguration {
    private final DSLContext dslContext;

    @Bean("jooqLinkDao")
    public JooqLinkDao jooqLinkDao() {
        return new JooqLinkDao(dslContext);
    }

    @Bean("jooqChatDao")
    public JooqChatDao jooqChatDao() {
        return new JooqChatDao(dslContext);
    }

    @Bean("jooqChatLinkDao")
    public JooqChatLinkDao jooqChatLinkDao() {
        return new JooqChatLinkDao(dslContext);
    }

    @Bean("jooqChatService")
    public JooqChatService jooqChatService() {
        return new JooqChatService(jooqChatDao(), jooqChatLinkDao());
    }

    @Bean("jooqLinkService")
    public JooqLinkService jooqLinkService() {
        return new JooqLinkService(jooqLinkDao(), jooqChatLinkDao(), jooqChatDao());
    }
}
