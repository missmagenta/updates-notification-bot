package edu.java.client.github.impl;

import edu.java.client.github.GitHubRepositoryService;
import edu.java.client.github.dto.EventResponse;
import edu.java.client.github.GitHubClient;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RequiredArgsConstructor
public class GithubClientImpl implements GitHubClient {
    private final GitHubRepositoryService githubRepositoryService;

    @Override
    public List<EventResponse> fetchEvents(String username, String repository) {
        return githubRepositoryService.getEvents(repository, username);
    }
}
