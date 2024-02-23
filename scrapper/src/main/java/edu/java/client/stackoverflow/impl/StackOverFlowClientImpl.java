package edu.java.client.stackoverflow.impl;

import edu.java.client.stackoverflow.StackOverFlowClient;
import edu.java.client.stackoverflow.StackOverFlowRepositoryService;
import edu.java.client.stackoverflow.dto.AnswerEventResponse;
import edu.java.client.stackoverflow.dto.CommentEventResponse;
import edu.java.client.stackoverflow.dto.RelatedQuestionsEventResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RequiredArgsConstructor
public class StackOverFlowClientImpl implements StackOverFlowClient {
    private final StackOverFlowRepositoryService stackOverFlowRepositoryService;

    @Override
    public List<AnswerEventResponse> fetchAnswerEvents(String questionId) {
        return stackOverFlowRepositoryService.getAnswerEvents(questionId);
    }

    @Override
    public List<CommentEventResponse> fetchCommentEvents(String questionId) {
        return stackOverFlowRepositoryService.getCommentsEvents(questionId);
    }

    @Override
    public List<RelatedQuestionsEventResponse> fetchRelatedQuestionsEvents(String questionId) {
        return stackOverFlowRepositoryService.getRelatedQuestionsEvents(questionId);
    }
}
