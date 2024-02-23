package edu.java.client.github.response;

import lombok.RequiredArgsConstructor;
import java.time.OffsetDateTime;

@RequiredArgsConstructor
public abstract class EventsResponse {

    private final String id;
    private final String eventType;
    private final OffsetDateTime createdAt;
}
