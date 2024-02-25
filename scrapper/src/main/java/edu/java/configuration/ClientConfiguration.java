package edu.java.configuration;

import edu.java.client.github.GitHubClient;
import edu.java.client.github.GitHubRepositoryService;
import edu.java.client.github.impl.GithubClientImpl;
import edu.java.client.stackoverflow.StackOverFlowClient;
import edu.java.client.stackoverflow.StackOverFlowRepositoryService;
import edu.java.client.stackoverflow.impl.StackOverFlowClientImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ClientConfiguration {
    public <T> T buildClient(Class<T> clientClass, String apiUrl, String contentType) {
        WebClient webClient = WebClient.builder()
            .baseUrl(apiUrl)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, contentType)
//            .defaultHeader(HttpHeaders.ACCEPT, apiVersion)
            .build();
        HttpServiceProxyFactory clientFactory =
            HttpServiceProxyFactory.builderFor(WebClientAdapter.create(webClient)).build();
        return clientFactory.createClient(clientClass);
    }

    private GitHubRepositoryService githubRepositoryService() {
        return buildClient(
            GitHubRepositoryService.class,
            GitHubAPIConfiguration.API_BASE_URL,
            GitHubAPIConfiguration.JSON_CONTENT_TYPE);
//            GitHubAPIConfiguration.API_VERSION_SPEC);
    }
    private StackOverFlowRepositoryService stackOverFlowRepositoryService() {
        return buildClient(
            StackOverFlowRepositoryService.class,
            StackOverFlowAPIConfiguration.API_BASE_URL,
            StackOverFlowAPIConfiguration.JSON_CONTENT_TYPE);
//            StackOverFlowAPIConfiguration.API_VERSION_SPEC);
    }

    @Bean
    GitHubClient githubClient() {
        return new GithubClientImpl(githubRepositoryService()); }

    @Bean StackOverFlowClient stackOverFlowClient() {
        return new StackOverFlowClientImpl(stackOverFlowRepositoryService()); }
}
