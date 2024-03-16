package edu.java.client.github;

import edu.java.client.github.dto.EventResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.origin.SystemEnvironmentOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import java.util.List;

public interface GitHubRepositoryService {
    @GetExchange("/repos/{owner}/{repo}/events")
    List<EventResponse> getEvents(
        @PathVariable("repo") @NotNull String repo,
        @PathVariable("owner") @NotNull String owner);
}
