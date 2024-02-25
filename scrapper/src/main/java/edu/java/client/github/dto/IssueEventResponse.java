package edu.java.client.github.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.java.client.github.dto.records.IssueResponse;
import lombok.Getter;
import java.io.Serializable;

@Getter
public class IssueEventResponse extends EventResponse {

    @JsonProperty("payload")
    private EventPayload payload;

    @Getter
    public static class EventPayload implements Serializable {
        @JsonProperty("action")
        private String action;
        @JsonProperty("issue")
        private IssueResponse issue;
    }
}
