package edu.java.client.github.dto;

import lombok.RequiredArgsConstructor;
import java.time.OffsetDateTime;

@RequiredArgsConstructor
public abstract class EventResponse {

    private final String id;
    private final String eventType;
    private final OffsetDateTime createdAt;
}
