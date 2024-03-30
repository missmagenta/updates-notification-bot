package edu.java.service.stackoverflow;

import edu.java.client.stackoverflow.StackOverFlowClient;
import edu.java.client.stackoverflow.dto.ListAnswersResponse;
import edu.java.client.stackoverflow.dto.ListCommentsResponse;
import edu.java.client.stackoverflow.dto.ListRelatedQuestionsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class StackOverFlowUpdateHandler {
    private final StackOverFlowClient stackOverFlowClient;

    public List<String> handleAnswers(String questionId, String lastUpdateDate) {
        LocalDateTime lastUpdateLocal = ZonedDateTime.parse(lastUpdateDate).toLocalDateTime();
        return stackOverFlowClient.fetchAnswerEvents(questionId, lastUpdateLocal)
            .getAnswers()
            .stream()
            .filter(ans -> ans.creationDate().isAfter(OffsetDateTime.parse(lastUpdateDate)))
            .map(this::getAnswerContents)
            .toList();
    }

    public List<String> handleComments(String questionId, String lastUpdateDate) {
        LocalDateTime lastUpdateLocal = ZonedDateTime.parse(lastUpdateDate).toLocalDateTime();
        return stackOverFlowClient.fetchCommentEvents(questionId, lastUpdateLocal)
            .getComments()
            .stream()
            .filter(ans -> ans.creationDate().isAfter(OffsetDateTime.parse(lastUpdateDate)))
            .map(this::getCommentContents)
            .toList();
    }

    public List<String> handleRelatedQuestions(String questionId, String lastUpdateDate) {
        LocalDateTime lastUpdateLocal = ZonedDateTime.parse(lastUpdateDate).toLocalDateTime();
        return stackOverFlowClient.fetchRelatedQuestionsEvents(questionId, lastUpdateLocal)
            .getRelatedQuestions()
            .stream()
            .filter(ans -> ans.creationDate().isAfter(OffsetDateTime.parse(lastUpdateDate)))
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
