package edu.java.client.stackoverflow.impl;

import edu.java.client.stackoverflow.StackOverFlowClient;
import edu.java.client.stackoverflow.StackOverFlowRepositoryService;
import edu.java.client.stackoverflow.dto.ListAnswersResponse;
import edu.java.client.stackoverflow.dto.ListCommentsResponse;
import edu.java.client.stackoverflow.dto.ListRelatedQuestionsResponse;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;

@RequiredArgsConstructor
public class StackOverFlowClientImpl implements StackOverFlowClient {
    private final StackOverFlowRepositoryService stackOverFlowRepositoryService;

    @Override
    public ListAnswersResponse fetchAnswerEvents(
        String questionId, LocalDateTime lastUpdate
        ) {
        return stackOverFlowRepositoryService.getAnswerEvents(questionId, null);
    }

    @Override
    public ListCommentsResponse fetchCommentEvents(
        String questionId, LocalDateTime lastUpdate) {
        return stackOverFlowRepositoryService.getCommentsEvents(questionId);
    }

    @Override
    public ListRelatedQuestionsResponse fetchRelatedQuestionsEvents(
        String questionId, LocalDateTime lastUpdate) {
        return stackOverFlowRepositoryService.getRelatedQuestionsEvents(questionId);
    }
}
