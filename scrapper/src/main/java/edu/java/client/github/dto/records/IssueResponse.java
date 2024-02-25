package edu.java.client.github.dto.records;

import com.fasterxml.jackson.annotation.JsonProperty;

public record IssueResponse(
    @JsonProperty("url") String url,
    @JsonProperty("html_url") String htmlUrl,
    @JsonProperty("state") String state,
    @JsonProperty("title") String title,
    @JsonProperty("body") String body,
    @JsonProperty("user") UserInformationResponse user
) {
}
