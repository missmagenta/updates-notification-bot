package edu.java.service;

import edu.java.client.github.GitHubClient;
import edu.java.client.github.dto.CommitCommentEventResponse;
import edu.java.client.github.dto.CreateEventResponse;
import edu.java.client.github.dto.EventResponse;
import edu.java.client.github.dto.IssueCommentEventResponse;
import edu.java.client.github.dto.PushEventResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class GitHubServiceHandler {

    private final GitHubClient gitHubClient;

    public GitHubServiceUpdateResult handle(
        String owner,
        String repository,
        String lastUpdateDate
    ) {
        System.out.println(owner + repository + lastUpdateDate);
        List<EventResponse> eventResponse = gitHubClient.fetchEvents(owner, repository)
            .stream()
            .filter(Objects::nonNull)
            .filter(resp -> resp.getCreatedAt().isAfter(OffsetDateTime.parse(lastUpdateDate)))
            .toList();
        System.out.println("resp " + eventResponse);

        GitHubServiceUpdateResult result = new GitHubServiceUpdateResult(eventResponse.stream().
            map(resp -> getEventDescription(resp)).filter(Objects::nonNull).toList());
        System.out.println(result);
        return result;
    }

    private String getEventDescription(EventResponse eventResponse) {
        if (eventResponse instanceof CommitCommentEventResponse commitCommentEventResponse) {
            return String.format(
                "Commit comment %s %s %s %s %s",
                commitCommentEventResponse.getPayload().getAction(),
                commitCommentEventResponse.getPayload().getComment().commitId(),
                commitCommentEventResponse.getPayload().getComment().body(),
                commitCommentEventResponse.getPayload().getComment().htmlUrl(),
                commitCommentEventResponse.getPayload().getComment().user()
            );
        } else if (eventResponse instanceof IssueCommentEventResponse issueCommentEventResponse) {
            return String.format(
                "%s %s %s %s %s %s %s %s %s %s %s %s %s",
                issueCommentEventResponse.getPayload().getAction(),
                issueCommentEventResponse.getPayload().getIssue().htmlUrl(),
                issueCommentEventResponse.getPayload().getIssue().url(),
                issueCommentEventResponse.getPayload().getIssue().title(),
                issueCommentEventResponse.getPayload().getIssue().state(),
                issueCommentEventResponse.getPayload().getIssue().body(),
                issueCommentEventResponse.getPayload().getIssue().user(),
                issueCommentEventResponse.getPayload().getComment().htmlUrl(),
                issueCommentEventResponse.getPayload().getComment().url(),
                issueCommentEventResponse.getPayload().getComment().issueUrl(),
                issueCommentEventResponse.getPayload().getComment().body(),
                issueCommentEventResponse.getPayload().getComment().updateDate(),
                issueCommentEventResponse.getPayload().getComment().user()
            );
        } else if (eventResponse instanceof CreateEventResponse createEventResponse) {

            return String.format(
                "Create Event %s %s",
                createEventResponse.getPayload().getRef(),
                createEventResponse.getPayload().getRefType()
                );
        } else if (eventResponse instanceof PushEventResponse pushEventResponse) {
            return String.format(
                "push Event %s %s %s",
                pushEventResponse.getPayload().getPushId(),
                pushEventResponse.getPayload().getCommits(),
                pushEventResponse.getPayload().getRef()
            );
        }
        return null;
    }
}
