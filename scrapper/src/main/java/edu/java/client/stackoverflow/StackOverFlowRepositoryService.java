package edu.java.client.stackoverflow;

import edu.java.client.stackoverflow.dto.ListAnswersResponse;
import edu.java.client.stackoverflow.dto.ListCommentsResponse;
import edu.java.client.stackoverflow.dto.ListRelatedQuestionsResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface StackOverFlowRepositoryService {
    @GetExchange("/questions/{id}/answers")
    ListAnswersResponse getAnswerEvents(
        @PathVariable("id") String questionId
    );

    @GetExchange("questions/{id}/comments")
    ListCommentsResponse getCommentsEvents(
        @PathVariable("id") String questionId
    );

    @GetExchange("questions/{id}/related")
    ListRelatedQuestionsResponse getRelatedQuestionsEvents(
        @PathVariable("id") String questionId
    );


}
