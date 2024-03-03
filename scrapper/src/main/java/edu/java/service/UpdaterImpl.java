package edu.java.service;

import edu.java.dto.request.LinkUpdateRequest;
import edu.java.service.github.GitHubUpdateHandler;
import edu.java.service.stackoverflow.StackOverFlowUpdateHandler;
import edu.java.service.update.BotUpdateSendingHandler;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RequiredArgsConstructor
public class UpdaterImpl implements Updater {
    private final BotUpdateSendingHandler botUpdateSendingHandler;
    private final GitHubUpdateHandler gitHubUpdateHandler;
    private final StackOverFlowUpdateHandler stackOverFlowUpdateHandler;

    @Override
    public void update() {

    }

    public void sendUpdates(int id, String url, String desc, List<Integer> chatIds) {
        botUpdateSendingHandler.handleSendingUpdates(new LinkUpdateRequest(
            id, url, desc, chatIds
        ));
    }
}
