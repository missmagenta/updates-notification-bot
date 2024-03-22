package edu.java.configuration.db;

import edu.java.domain.jpa.JpaChatDao;
import edu.java.domain.jpa.JpaLinkDao;
import edu.java.service.jpa.JpaChatService;
import edu.java.service.jpa.JpaLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "access-type", havingValue = "jpa")
@RequiredArgsConstructor
@ComponentScan("edu.java.domain.jpa")
public class JpaConfiguration {
    private final JpaChatDao jpaChatDao;
    private final JpaLinkDao jpaLinkDao;

    @Bean("jpaLinkService")
    public JpaLinkService jpaLinkService() {
        return new JpaLinkService(jpaLinkDao, jpaChatDao);
    }

    @Bean("jpaChatService")
    public JpaChatService jpaChatService() {
        return new JpaChatService(jpaChatDao, jpaLinkDao);
    }
}
