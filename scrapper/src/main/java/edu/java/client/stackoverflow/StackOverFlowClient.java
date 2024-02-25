package edu.java.client.stackoverflow;

import edu.java.client.stackoverflow.dto.ListAnswersResponse;
import edu.java.client.stackoverflow.dto.ListCommentsResponse;
import edu.java.client.stackoverflow.dto.ListRelatedQuestionsResponse;

public interface StackOverFlowClient {
    ListAnswersResponse fetchAnswerEvents(String questionId);
    ListCommentsResponse fetchCommentEvents(String questionId);
    ListRelatedQuestionsResponse fetchRelatedQuestionsEvents(String questionId);
}
