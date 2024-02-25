package edu.java.service.github;

import edu.java.client.github.GitHubClient;
import edu.java.client.github.dto.CommitCommentEventResponse;
import edu.java.client.github.dto.CreateEventResponse;
import edu.java.client.github.dto.EventResponse;
import edu.java.client.github.dto.IssueEventResponse;
import edu.java.client.github.dto.PushEventResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class GitHubUpdateHandler {

    private final GitHubClient gitHubClient;

    public GitHubServiceUpdateResult handle(
        String owner,
        String repository,
        String lastUpdateDate
    ) {
        List<EventResponse> eventResponse = gitHubClient.fetchEvents(owner, repository)
            .stream()
            .filter(Objects::nonNull)
            .filter(resp -> resp.getCreatedAt().isAfter(OffsetDateTime.parse(lastUpdateDate)))
            .toList();

        return new GitHubServiceUpdateResult(eventResponse.stream().
            map(this::getEventDescription).filter(Objects::nonNull).toList());
    }

    private String getEventDescription(EventResponse eventResponse) {
        if (eventResponse instanceof CommitCommentEventResponse commitCommentEventResponse) {
            return String.format(
                "Commit Comment Event. Action: %s. Commit id: %s. Body: %s. HTML URL: %s. User: %s%n",
                commitCommentEventResponse.getPayload().getAction(),
                commitCommentEventResponse.getPayload().getComment().commitId(),
                commitCommentEventResponse.getPayload().getComment().body(),
                commitCommentEventResponse.getPayload().getComment().htmlUrl(),
                commitCommentEventResponse.getPayload().getComment().user()
            );
        } else if (eventResponse instanceof IssueEventResponse issueCommentEventResponse) {
            return String.format(
                "Issue Comment Event. Action: %s. Issue HTML URL: %s. Issue URL: %s. Issue Title: %s. " +
                    "Issue State: %s.Issue Body: %s. Issue User: %s%n",
                issueCommentEventResponse.getPayload().getAction(),
                issueCommentEventResponse.getPayload().getIssue().htmlUrl(),
                issueCommentEventResponse.getPayload().getIssue().url(),
                issueCommentEventResponse.getPayload().getIssue().title(),
                issueCommentEventResponse.getPayload().getIssue().state(),
                issueCommentEventResponse.getPayload().getIssue().body(),
                issueCommentEventResponse.getPayload().getIssue().user()
            );
        } else if (eventResponse instanceof CreateEventResponse createEventResponse) {

            return String.format(
                "Create Event. Ref: %s. Ref type: %s%n",
                createEventResponse.getPayload().getRef(),
                createEventResponse.getPayload().getRefType()
                );
        } else if (eventResponse instanceof PushEventResponse pushEventResponse) {
            return String.format(
                "Push Event. Push ID: %s. Commits pushed: %s. Ref: %s%n",
                pushEventResponse.getPayload().getPushId(),
                pushEventResponse.getPayload().getCommits(),
                pushEventResponse.getPayload().getRef()
            );
        }
        return null;
    }
}
