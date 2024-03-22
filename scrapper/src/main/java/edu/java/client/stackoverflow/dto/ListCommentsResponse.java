package edu.java.client.stackoverflow.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
public class ListCommentsResponse {
    @JsonProperty("items")
    private List<CommentEventResponse> comments;

    public record CommentEventResponse(
        @JsonProperty("owner") UserInformationResponse owner,
        @JsonProperty("comment_id") long id,
        @JsonProperty("score") int score,
        @JsonProperty("creation_date") OffsetDateTime creationDate,
        @JsonProperty("body") String body
    ) {
    }
}

