package edu.java.client.github.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.time.OffsetDateTime;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = CommitCommentEventResponse.class, name = "CommitCommentEvent"),
    @JsonSubTypes.Type(value = CreateEventResponse.class, name = "CreateEvent"),
    @JsonSubTypes.Type(value = IssueCommentEventResponse.class, name = "IssuesCommentEvent"),
    @JsonSubTypes.Type(value = PushEventResponse.class, name = "PushEvent"),

})
@Getter
@Setter
public abstract class EventResponse implements Serializable {

    @JsonProperty("id")
    private String id;
    @JsonProperty("type")
    private String eventType;
    @JsonProperty("created_at")
    private OffsetDateTime createdAt;
}
