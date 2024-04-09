package edu.java.configuration.client;

import edu.java.client.bot.BotClient;
import edu.java.client.bot.BotRepositoryService;
import edu.java.client.bot.impl.BotClientImpl;
import edu.java.client.github.GitHubClient;
import edu.java.client.github.GitHubRepositoryService;
import edu.java.client.github.impl.GithubClientImpl;
import edu.java.client.retry.Retry;
import edu.java.client.stackoverflow.StackOverFlowClient;
import edu.java.client.stackoverflow.StackOverFlowRepositoryService;
import edu.java.client.stackoverflow.impl.StackOverFlowClientImpl;
import edu.java.configuration.ApplicationConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.core.publisher.Mono;

@Configuration
@RequiredArgsConstructor
public class ClientConfiguration {
    private final ApplicationConfig applicationConfig;
    private final Retry retry;

    public <T> T buildClient(Class<T> clientClass, String apiUrl, String contentType) {
        WebClient webClient = WebClient.builder()
            .baseUrl(apiUrl)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, contentType)

            .exchangeStrategies(ExchangeStrategies
                .builder()
                .codecs(codecs -> codecs
                    .defaultCodecs()
                    .maxInMemorySize(500 * 1024 * 1024))
                .build())

            .filter(this::applyRetry)
            .build();
        HttpServiceProxyFactory clientFactory =
            HttpServiceProxyFactory.builderFor(WebClientAdapter.create(webClient)).build();
        return clientFactory.createClient(clientClass);
    }

    private Mono<ClientResponse> applyRetry(ClientRequest request, ExchangeFunction next) {
        return next.exchange(request)
            .retryWhen(retry.toReactorRetry())
            .flatMap(Mono::just);
    }

    private GitHubRepositoryService githubRepositoryService() {
        return buildClient(
            GitHubRepositoryService.class,
            GitHubAPIConfiguration.API_BASE_URL,
            GitHubAPIConfiguration.JSON_CONTENT_TYPE
        );
    }

    private StackOverFlowRepositoryService stackOverFlowRepositoryService() {
        return buildClient(
            StackOverFlowRepositoryService.class,
            StackOverFlowAPIConfiguration.API_BASE_URL,
            StackOverFlowAPIConfiguration.JSON_CONTENT_TYPE
        );
    }

    private BotRepositoryService botRepositoryService() {
        return buildClient(
            BotRepositoryService.class,
            applicationConfig.botApiUrl(),
            MediaType.APPLICATION_JSON_VALUE
        );
    }

    @Bean
    public GitHubClient githubClient() {
        return new GithubClientImpl(githubRepositoryService());
    }

    @Bean
    public StackOverFlowClient stackOverFlowClient() {
        return new StackOverFlowClientImpl(stackOverFlowRepositoryService());
    }

    @Bean
    public BotClient botClient() {
        return new BotClientImpl(botRepositoryService());
    }
}
