package edu.java.service.update;

import edu.java.dto.request.LinkUpdateRequest;

public interface UpdateSender {
    void sendUpdate(LinkUpdateRequest linkUpdateRequest);
}
