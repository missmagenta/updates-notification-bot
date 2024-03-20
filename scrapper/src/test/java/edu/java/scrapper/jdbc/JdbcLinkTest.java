package edu.java.scrapper.jdbc;

import edu.java.domain.dao.jdbc.JdbcLinkDao;
import edu.java.dto.response.LinkResponse;
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
public class JdbcLinkTest extends IntegrationEnvironment {
    @Autowired
    private JdbcLinkDao linkDao;

    @Test
    @Transactional
    @Rollback
    void addTest() {
        String url = "https://example.com";
        linkDao.add(url);

        List<LinkResponse> links = linkDao.findAll();

        assertAll(
            () -> assertEquals(1, links.size()),
            () -> assertEquals(url, links.get(0).url())
        );
    }

    @Test
    @Transactional
    @Rollback
    void removeTest() {
        String url = "https://example.com";
        linkDao.add(url);

        Integer id = linkDao.findIdByUrl(url);
        linkDao.remove(id);
        List<LinkResponse> links = linkDao.findAll();

        assertTrue(links.isEmpty());
    }
}
