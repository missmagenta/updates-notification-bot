package edu.java.scrapper.jpa;

import edu.java.domain.jpa.JpaChatDao;
import edu.java.domain.jpa.JpaLinkDao;
import edu.java.dto.response.LinkResponse;
import edu.java.model.Chat;
import edu.java.model.Link;
import edu.java.scrapper.IntegrationEnvironment;
import edu.java.service.jpa.JpaLinkService;
import java.time.LocalDateTime;
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
public class JpaLinkServiceTest extends IntegrationEnvironment {

    @Autowired
    JpaLinkDao jpaLinkDao;

    @Autowired
    JpaChatDao jpaChatDao;

    @Autowired
    JpaLinkService jpaLinkService;

    @Test
    void addTest() {
        Chat chat = new Chat();
        chat.setId(666);
        jpaChatDao.save(chat);

        Link link = new Link();
        String url = "https://example.com";
        link.setName(url);
        link.setLastUpdateDate(LocalDateTime.now());


        LinkResponse response = jpaLinkService.add(chat.getId(), url);
        Link addedLink = jpaLinkDao.findById(response.id()).get();

        assertAll(
            () -> assertNotNull(response),
            () -> assertEquals(url, response.url()),
            () -> assertEquals(link.getName(), response.url()),
            () -> assertEquals(1, addedLink.getChats().size()),
            () -> assertEquals(chat, addedLink.getChats().get(0))
        );
    }

    @Test
    void deleteLink() {
        Chat chat = new Chat();
        chat.setId(666);
        jpaChatDao.save(chat);

        Link link = new Link();
        String url = "https://example.com";
        link.setName(url);
        link.setLastUpdateDate(LocalDateTime.now());
        link.getChats().add(chat);
        jpaLinkDao.save(link);

        LinkResponse response = jpaLinkService.remove(chat.getId(), link.getName());

        assertAll(
            () -> assertNull(response)
        );

    }
}
