package edu.java.client.stackoverflow;

import edu.java.client.stackoverflow.dto.AnswerEventResponse;
import edu.java.client.stackoverflow.dto.CommentEventResponse;
import edu.java.client.stackoverflow.dto.RelatedQuestionsEventResponse;
import java.util.List;

public interface StackOverFlowClient {
    List<AnswerEventResponse> fetchAnswerEvents(String questionId);
    List<CommentEventResponse> fetchCommentEvents(String questionId);
    List<RelatedQuestionsEventResponse> fetchRelatedQuestionsEvents(String questionId);
}
