package edu.java.client.github.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.java.client.github.dto.records.IssueCommentResponse;
import edu.java.client.github.dto.records.IssueResponse;
import lombok.Getter;
import java.io.Serializable;

@Getter
public class IssueCommentEventResponse extends EventResponse {

    @JsonProperty("payload")
    private EventPayload payload;

    @Getter
    public static class EventPayload implements Serializable {
        @JsonProperty("action")
        private String action;
//        @JsonProperty("changes")
//        private IssueCommentResponse changes;
        @JsonProperty("issue")
        private IssueResponse issue;
        @JsonProperty("comment")
        private IssueCommentResponse comment;
    }
}
