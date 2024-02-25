package edu.java.client.stackoverflow.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;


public class ListRelatedQuestionsResponse {
    public record RelatedQuestionsEventResponse(
        @JsonProperty("owner") UserInformationResponse owner,
        @JsonProperty("question_id") long questionId,
        @JsonProperty("title") String title,
        @JsonProperty("creation_date") OffsetDateTime creationDate
    ) {
    }
}

