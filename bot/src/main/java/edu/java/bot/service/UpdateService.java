package edu.java.bot.service;

import edu.java.dto.request.LinkUpdateRequest;

public interface UpdateService {
    void sendUpdate(LinkUpdateRequest linkUpdateRequest);
}
