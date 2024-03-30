package edu.java.service;

import edu.java.dto.request.LinkUpdateRequest;
import edu.java.dto.response.ListLinkResponse;
import edu.java.linkpraser.parser.LinkParserHandler;
import edu.java.linkpraser.parsingresult.GithubParsingResult;
import edu.java.linkpraser.parsingresult.ParsingResult;
import edu.java.linkpraser.parsingresult.StackOverFlowParsingResult;
import edu.java.model.Chat;
import edu.java.model.Link;
import edu.java.service.github.GitHubServiceUpdateResult;
import edu.java.service.github.GitHubUpdateHandler;
import edu.java.service.stackoverflow.StackOverFlowUpdateHandler;
import edu.java.service.update.RestBotUpdateSender;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class LinkUpdaterImpl implements LinkUpdater {
    private final RestBotUpdateSender restBotUpdateSender;
    private final LinkParserHandler linkParserHandler;
    private final GitHubUpdateHandler gitHubUpdateHandler;
    private final StackOverFlowUpdateHandler stackOverFlowUpdateHandler;

    private final LinkService linkService;
    private static final Long PERIOD_TO_CHECK_LINK_SECONDS = 500L;

    public LinkUpdaterImpl(
        RestBotUpdateSender restBotUpdateSender,
        LinkParserHandler linkParserHandler,
        GitHubUpdateHandler gitHubUpdateHandler,
        StackOverFlowUpdateHandler stackOverFlowUpdateHandler,
        @Qualifier("jpaLinkService") LinkService linkService
    ) {
        this.restBotUpdateSender = restBotUpdateSender;
        this.linkParserHandler = linkParserHandler;
        this.gitHubUpdateHandler = gitHubUpdateHandler;
        this.stackOverFlowUpdateHandler = stackOverFlowUpdateHandler;
        this.linkService = linkService;
    }

    @Override
    public void update() {
        ListLinkResponse response = linkService.findNotUpdatedForLongTime(PERIOD_TO_CHECK_LINK_SECONDS);
        response.linkResponses().forEach(link -> {
            Link linkEntity = linkService.findLinkById(link.id());
            List<Integer> chatIds = linkEntity.getChats()
                .stream()
                .map(Chat::getId)
                .toList();

            ParsingResult parsingResult = linkParserHandler.checkLink(link.url());
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
                linkEntity.getLastUpdateDate().toString()
            );

            List<String> comments = stackOverFlowUpdateHandler.handleComments(
                stackOverFlowParsingResult.questionId(),
                linkEntity.getLastUpdateDate().toString()
            );

            List<String> relatedQuestions = stackOverFlowUpdateHandler.handleRelatedQuestions(
                stackOverFlowParsingResult.questionId(),
                linkEntity.getLastUpdateDate().toString()
            );

            if (!answers.isEmpty() || !comments.isEmpty() || !relatedQuestions.isEmpty()) {
                linkService.update(linkEntity.getId(), LocalDateTime.now());
            }

            return Stream.of(answers, comments, relatedQuestions)
                .flatMap(Collection::stream)
                .toList();
        }
        return Collections.emptyList();
    }
}
