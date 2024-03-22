package edu.java.scrapper.jdbc;

import edu.java.domain.dao.jdbc.JdbcChatDao;
import edu.java.domain.dao.jdbc.JdbcChatLinkDao;
import edu.java.model.Chat;
import edu.java.scrapper.IntegrationEnvironment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestPropertySource(properties = "spring.liquibase.enabled=false")
public class JdbcChatTest extends IntegrationEnvironment {
    @Autowired
    private JdbcChatDao chatDao;

    @Test
    @Transactional
    @Rollback
    void addTest() {
        Integer id = 123;

        chatDao.add(id);
        List<Chat> chats = chatDao.findAll();

        assertAll(
            () -> assertEquals(1, chats.size()),
            () -> assertEquals(id, chats.get(0).getId())
        );
    }

    @Test
    @Transactional
    @Rollback
    void removeTest() {
        Integer id = 888;
        chatDao.add(id);

        chatDao.remove(id);
        List<Chat> chats = chatDao.findAll();

        assertTrue(chats.isEmpty());
    }
}
