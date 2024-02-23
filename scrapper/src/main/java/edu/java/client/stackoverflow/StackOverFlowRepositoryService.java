package edu.java.client.stackoverflow;

import edu.java.client.stackoverflow.dto.AnswerEventResponse;
import edu.java.client.stackoverflow.dto.CommentEventResponse;
import edu.java.client.stackoverflow.dto.RelatedQuestionsEventResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import java.util.List;

public interface StackOverFlowRepositoryService {
    @GetExchange("/questions/{id}/answers")
    List<AnswerEventResponse> getAnswerEvents(
        @PathVariable("id") String questionId
    );

    @GetExchange("questions/{id}/comments")
    List<CommentEventResponse> getCommentsEvents(
        @PathVariable("id") String questionId
    );

    @GetExchange("questions/{ids}/related")
    List<RelatedQuestionsEventResponse> getRelatedQuestionsEvents(
        @PathVariable("id") String questionId
    );


}
