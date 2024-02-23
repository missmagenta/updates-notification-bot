package edu.java.configuration;

import edu.java.client.github.GithubClient;
import edu.java.client.github.impl.GithubClientImpl;
import edu.java.client.stackoverflow.StackOverFlowClient;
import edu.java.client.stackoverflow.impl.StackOverFlowClientImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfiguration {

    private WebClient createWebClient(String url) {

        WebClient webClient = WebClient.builder()
            .baseUrl(url)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

        return webClient;
    }

    @Bean
    GithubClient githubClient() {
        return new GithubClientImpl();}

    @Bean StackOverFlowClient stackOverFlowClient() {
        return new StackOverFlowClientImpl(); }
}
