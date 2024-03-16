package edu.java.service;

import edu.java.dto.request.LinkUpdateRequest;
import edu.java.dto.response.ListLinkResponse;
import edu.java.linkpraser.parser.LinkParser;
import edu.java.linkpraser.parsingresult.ParsingResult;
import edu.java.linkpraser.parsingresult.GithubParsingResult;
import edu.java.linkpraser.parsingresult.StackOverFlowParsingResult;
import edu.java.model.Chat;
import edu.java.model.Link;
import edu.java.service.github.GitHubServiceUpdateResult;
import edu.java.service.github.GitHubUpdateHandler;
import edu.java.service.jdbc.JdbcLinkService;
import edu.java.service.stackoverflow.StackOverFlowUpdateHandler;
import edu.java.service.update.RestBotUpdateSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LinkUpdaterImpl implements LinkUpdater {
    private final RestBotUpdateSender restBotUpdateSender;
    private final LinkParser linkParser;
    private final GitHubUpdateHandler gitHubUpdateHandler;
    private final StackOverFlowUpdateHandler stackOverFlowUpdateHandler;
    private final JdbcLinkService linkService;
    private static final Long PERIOD_TO_CHECK_LINK_SECONDS = 500L;

    @Override
    public void update() {
        ListLinkResponse response = linkService.findNotUpdatedForLongTime(PERIOD_TO_CHECK_LINK_SECONDS);
        response.linkResponses().forEach(link -> {
            Link linkEntity = linkService.findLinkById(link.id());
            List<Integer> chatIds = linkEntity.getChats()
                .stream()
                .map(Chat::getId)
                .toList();

            ParsingResult parsingResult = linkParser.parse(link.url());
            List<String> eventDescriptions = collectEventUpdatesFromExternalHandlers(parsingResult, linkEntity);
            eventDescriptions.forEach(eventDescription ->
                restBotUpdateSender.sendUpdates(new LinkUpdateRequest(
                    linkEntity.getId(),
                    linkEntity.getName(),
                    eventDescription,
                    chatIds
                ))
            );
        });
    }

    private List<String> collectEventUpdatesFromExternalHandlers(ParsingResult parsingResult, Link linkEntity) {
        if (parsingResult instanceof GithubParsingResult githubParsingResult) {
            GitHubServiceUpdateResult result = gitHubUpdateHandler.handle(
                githubParsingResult.username(),
                githubParsingResult.repository(),
                linkEntity.getLastUpdateDate().toString()
            );

            List<String> eventDescriptions = result.eventDescriptions();

            if (!eventDescriptions.isEmpty()) {
                linkService.update(linkEntity.getId(), LocalDateTime.now());
            }
            return eventDescriptions;
        } else if (parsingResult instanceof StackOverFlowParsingResult stackOverFlowParsingResult) {
            List<String> answers = stackOverFlowUpdateHandler.handleAnswers(
                stackOverFlowParsingResult.questionId(),
                linkEntity.getLastUpdateDate()
            );

            List<String> comments = stackOverFlowUpdateHandler.handleComments(
                stackOverFlowParsingResult.questionId(),
                linkEntity.getLastUpdateDate()
            );

            List<String> relatedQuestions = stackOverFlowUpdateHandler.handleRelatedQuestions(
                stackOverFlowParsingResult.questionId(),
                linkEntity.getLastUpdateDate()
            );

            if (!answers.isEmpty() || !comments.isEmpty() || !relatedQuestions.isEmpty()) {
                linkService.update(linkEntity.getId(), LocalDateTime.now());
            }

            List<String> combinedList = new ArrayList<>();
            combinedList.addAll(answers);
            combinedList.addAll(comments);
            combinedList.addAll(relatedQuestions);

            return combinedList;
        }
        return Collections.emptyList();
    }
}
