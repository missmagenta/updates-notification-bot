package edu.java.client.github.dto.records;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CommitCommentResponse(
    @JsonProperty("html_url") String htmlUrl,
    @JsonProperty("body") String body,
    @JsonProperty("commit_id") String commitId,
    @JsonProperty("user") UserInformationResponse user
) {
}
