package edu.java.service.stackoverflow;

import edu.java.client.stackoverflow.StackOverFlowClient;
import edu.java.client.stackoverflow.dto.ListAnswersResponse;
import edu.java.client.stackoverflow.dto.ListCommentsResponse;
import edu.java.client.stackoverflow.dto.ListRelatedQuestionsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class StackOverFlowUpdateHandler {
    private final StackOverFlowClient stackOverFlowClient;

    public List<String> handleAnswers(String questionId, LocalDateTime lastUpdateDate) {

        return stackOverFlowClient.fetchAnswerEvents(questionId, lastUpdateDate)
            .getAnswers()
            .stream()
            .filter(ans -> ans.creationDate().isAfter(lastUpdateDate.atOffset(ZoneOffset.UTC)))
            .map(this::getAnswerContents)
            .toList();
    }

    public List<String> handleComments(String questionId, LocalDateTime lastUpdate) {
        return stackOverFlowClient.fetchCommentEvents(questionId, lastUpdate)
            .getComments()
            .stream()
            .filter(ans -> ans.creationDate().isAfter(lastUpdate.atOffset(ZoneOffset.UTC)))
            .map(this::getCommentContents)
            .toList();
    }

    public List<String> handleRelatedQuestions(String questionId, LocalDateTime lastUpdate) {
        return stackOverFlowClient.fetchRelatedQuestionsEvents(questionId, lastUpdate)
            .getRelatedQuestions()
            .stream()
            .filter(ans -> ans.creationDate().isAfter(lastUpdate.atOffset(ZoneOffset.UTC)))
            .map(this::getRelationQuestion)
            .toList();
    }

    private String getAnswerContents(ListAnswersResponse.AnswerEventResponse answerEventResponse) {
        return String.format(
            "Answer with id: %s. Answer: %s. Creation date: %s. Is accepted: %s. Score: %s. Owner: %s",
            answerEventResponse.answerId(),
            answerEventResponse.body(),
            answerEventResponse.creationDate(),
            answerEventResponse.isAccepted(),
            answerEventResponse.score(),
            answerEventResponse.owner()
        );
    }

    private String getCommentContents(ListCommentsResponse.CommentEventResponse commentEventResponse) {
        return String.format(
            "Comment with id: %s. Comment: %s. Score: %s. Creation date: %s. Owner: %s",
            commentEventResponse.id(),
            commentEventResponse.body(),
            commentEventResponse.score(),
            commentEventResponse.creationDate(),
            commentEventResponse.owner()
        );
    }

    private String getRelationQuestion(ListRelatedQuestionsResponse.RelatedQuestionsEventResponse response) {
        return String.format(
            "Question elated to the one you follow. Question id: %s. Title: %s. Creation date: %s. Owner: %s",
            response.questionId(),
            response.title(),
            response.creationDate(),
            response.owner()
        );
    }
}
