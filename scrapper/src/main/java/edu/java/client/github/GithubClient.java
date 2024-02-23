package edu.java.client.github;

import edu.java.client.github.response.EventsResponse;

public interface GithubClient {
    EventsResponse fetchEvents(String username, String repository);
}
