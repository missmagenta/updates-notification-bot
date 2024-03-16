package edu.java.client.stackoverflow;

import edu.java.client.stackoverflow.dto.ListAnswersResponse;
import edu.java.client.stackoverflow.dto.ListCommentsResponse;
import edu.java.client.stackoverflow.dto.ListRelatedQuestionsResponse;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public interface StackOverFlowClient {
    ListAnswersResponse fetchAnswerEvents(
        @NotNull String questionId,
        LocalDateTime lastUpdate
        );
    ListCommentsResponse fetchCommentEvents(
        @NotNull String questionId,
        LocalDateTime lastUpdate);
    ListRelatedQuestionsResponse fetchRelatedQuestionsEvents(
        @NotNull String questionId,
        LocalDateTime lastUpdate);
}
