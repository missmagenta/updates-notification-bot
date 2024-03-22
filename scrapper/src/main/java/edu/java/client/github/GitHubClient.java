package edu.java.client.github;

import edu.java.client.github.dto.EventResponse;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public interface GitHubClient {
    List<EventResponse> fetchEvents(
        @NotNull String username,
        @NotNull String repository);
}
