package edu.java.service.stackoverflow;

import edu.java.client.stackoverflow.StackOverFlowClient;
import edu.java.client.stackoverflow.dto.ListAnswersResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class StackOverFlowUpdateHandler {
    private final StackOverFlowClient stackOverFlowClient;

    public List<String> handleAnswers(String questionId, String lastUpdateDate) {

        return stackOverFlowClient.fetchAnswerEvents(questionId)
            .getAnswers()
            .stream()
            .map(this::getAnswerContents)
            .toList();
    }

    private String getAnswerContents(ListAnswersResponse.AnswerEventResponse answerEventResponse) {
        return String.format("Answer id: %s. Answer: %s. Creation date: %s. Is accepted: %s. Score: %s. Owner: %s",
                    answerEventResponse.answerId(),
                    answerEventResponse.body(),
                    answerEventResponse.creationDate(),
                    answerEventResponse.isAccepted(),
                    answerEventResponse.score(),
                    answerEventResponse.owner());
    }
}
