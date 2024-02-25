package edu.java.client.stackoverflow.impl;

import edu.java.client.stackoverflow.StackOverFlowClient;
import edu.java.client.stackoverflow.StackOverFlowRepositoryService;
import edu.java.client.stackoverflow.dto.ListAnswersResponse;
import edu.java.client.stackoverflow.dto.ListCommentsResponse;
import edu.java.client.stackoverflow.dto.ListRelatedQuestionsResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StackOverFlowClientImpl implements StackOverFlowClient {
    private final StackOverFlowRepositoryService stackOverFlowRepositoryService;

    @Override
    public ListAnswersResponse fetchAnswerEvents(String questionId) {
        return stackOverFlowRepositoryService.getAnswerEvents(questionId);
    }

    @Override
    public ListCommentsResponse fetchCommentEvents(String questionId) {
        return stackOverFlowRepositoryService.getCommentsEvents(questionId);
    }

    @Override
    public ListRelatedQuestionsResponse fetchRelatedQuestionsEvents(String questionId) {
        return stackOverFlowRepositoryService.getRelatedQuestionsEvents(questionId);
    }
}
