package edu.java.scrapper;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import edu.java.client.github.GitHubClient;
import edu.java.client.stackoverflow.StackOverFlowClient;
import edu.java.configuration.ClientConfiguration;
import edu.java.service.github.GitHubServiceUpdateResult;
import edu.java.service.github.GitHubUpdateHandler;
import edu.java.service.stackoverflow.StackOverFlowUpdateHandler;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
@Slf4j
public class ClientIntegrationTest {

    private ClientConfiguration clientConfiguration;
    private GitHubClient gitHubClient;
    private StackOverFlowClient stackOverFlowClient;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule();

    @Before
    public void setUp() {
        clientConfiguration = new ClientConfiguration();
        gitHubClient = clientConfiguration.githubClient();
        stackOverFlowClient = clientConfiguration.stackOverFlowClient();
    }

    @Test
    public void testGitHubClient() {
        stubFor(get(urlEqualTo("/repos/JetBrains/kotlin/events"))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")));


        GitHubUpdateHandler gitHubServiceHandler = new GitHubUpdateHandler(gitHubClient);

        GitHubServiceUpdateResult result = gitHubServiceHandler.handle("JetBrains", "kotlin", "2024-01-01T00:00:00Z");

        assertNotNull(result);
    }

    @Test
    public void testStackOverFlowAnswers() {
        stubFor(get(urlEqualTo("/questions/{id}/answers"))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")));

        StackOverFlowUpdateHandler stackOverFlowUpdateHandler = new StackOverFlowUpdateHandler(stackOverFlowClient);
        List<String> answers = stackOverFlowUpdateHandler.handleAnswers("78057817", "2024-01-01T00:00:00Z");

        assertNotNull(answers);
    }
}
