package edu.java.client.github.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.java.client.github.dto.records.CommitCommentResponse;
import lombok.Getter;
import java.io.Serializable;

@Getter
public class CommitCommentEventResponse extends EventResponse {

    @JsonProperty("payload")
    private EventPayload payload;

    @Getter
    public static class EventPayload implements Serializable {
        @JsonProperty("action")
        private String action;

        @JsonProperty("comment")
        private CommitCommentResponse comment;
    }
}
