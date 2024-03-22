package edu.java.scrapper.jpa;

import edu.java.domain.jpa.JpaChatDao;
import edu.java.model.Chat;
import edu.java.scrapper.IntegrationEnvironment;
import edu.java.service.jpa.JpaChatService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@TestPropertySource(properties = "spring.liquibase.enabled=false")
public class JpaChatServiceTest extends IntegrationEnvironment {
    @Autowired
    private JpaChatDao jpaChatDao;

    @Autowired
    private JpaChatService jpaChatService;

    @Test
    void addTest() {
        Integer chatId = 123;
        jpaChatService.register(chatId);

        Chat chat = jpaChatDao.findById(chatId).orElse(null);
        assertAll(
            () -> assertNotNull(chat),
            () -> assertEquals(chatId, chat.getId())
        );
    }

    @Test
    void deleteTest() {
        Integer chatId = 666;
        Chat chat = new Chat();
        chat.setId(chatId);
        jpaChatDao.save(chat);

        jpaChatService.unregister(chatId);

        Chat deletedChat = jpaChatDao.findById(chatId).orElse(null);
        assertNull(deletedChat);
    }
}
