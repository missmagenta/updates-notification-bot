package edu.java.client.github.dto.records;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;

public record IssueCommentResponse(
    @JsonProperty("url") String url,
    @JsonProperty("html_url") String htmlUrl,
    @JsonProperty("body") String body,
    @JsonProperty("user") UserInformationResponse user,
    @JsonProperty("updated_at") OffsetDateTime updateDate,
    @JsonProperty("issue_url") String issueUrl
    ) {
}
