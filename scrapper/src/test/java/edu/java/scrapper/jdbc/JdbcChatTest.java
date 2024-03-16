package edu.java.scrapper.jdbc;

import edu.java.domain.dao.jdbc.JdbcChatDao;
import edu.java.dto.response.LinkResponse;
import edu.java.model.Chat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class JdbcChatTest {
    @Autowired
    private JdbcChatDao chatRepository;

    @Test
    @Transactional
    @Rollback
    void addTest() {
        Integer id = 123;

        chatRepository.add(id);
        List<Chat> chats = chatRepository.findAll();

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
        chatRepository.add(id);

        chatRepository.remove(id);
        List<Chat> chats = chatRepository.findAll();

        assertTrue(chats.isEmpty());
    }
}
