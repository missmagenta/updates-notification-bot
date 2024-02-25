package edu.java.scrapper;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import edu.java.client.github.GitHubClient;
import edu.java.client.github.GitHubRepositoryService;
import edu.java.client.github.dto.EventResponse;
import edu.java.client.github.impl.GithubClientImpl;
import edu.java.configuration.ClientConfiguration;
import edu.java.configuration.GitHubAPIConfiguration;
import edu.java.service.GitHubServiceHandler;
import edu.java.service.GitHubServiceUpdateResult;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertEquals;

@SpringBootTest
public class ClientIntegrationTest {

    private ClientConfiguration clientConfiguration;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule();

    @Before
    public void setUp() {
        // Создаем и настраиваем экземпляр ClientConfiguration перед каждым тестом
        clientConfiguration = new ClientConfiguration();
        // Выполняем необходимую настройку, например:
        // clientConfiguration.setSomeProperty(someValue);
    }

    @Test
    public void testHandle() throws Exception {
        stubFor(get(urlEqualTo("/repos/missmagenta/burning-man/events"))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")));

        GithubClientImpl gitHubClient = new GithubClientImpl(clientConfiguration.buildClient(GitHubRepositoryService.class,
            GitHubAPIConfiguration.API_BASE_URL,
            GitHubAPIConfiguration.JSON_CONTENT_TYPE,
            GitHubAPIConfiguration.API_VERSION_SPEC));
        GitHubServiceHandler gitHubServiceHandler = new GitHubServiceHandler(gitHubClient);


        GitHubServiceUpdateResult result = gitHubServiceHandler.handle("missmagenta", "burning-man", "2023-06-01T00:00:00Z");
        System.out.println(result);

        List<String> expectedDescriptions = Arrays.asList(
            // to do
        );
//        assertEquals(expectedDescriptions, result.eventDescriptions());
    }
}
