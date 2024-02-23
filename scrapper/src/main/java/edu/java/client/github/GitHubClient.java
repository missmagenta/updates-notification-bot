package edu.java.client.github;

import edu.java.client.github.dto.EventResponse;
import java.util.List;

public interface GitHubClient {
    List<EventResponse> fetchEvents(String username, String repository);
}
