package edu.java.client.stackoverflow.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
public class ListRelatedQuestionsResponse {
    @JsonProperty("items")
    private List<RelatedQuestionsEventResponse> relatedQuestions;

    public record RelatedQuestionsEventResponse(
        @JsonProperty("owner") UserInformationResponse owner,
        @JsonProperty("question_id") long questionId,
        @JsonProperty("title") String title,
        @JsonProperty("creation_date") OffsetDateTime creationDate
    ) {
    }
}

