package edu.java.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record LinkUpdateRequest(
    @JsonProperty("id") int id,
    @JsonProperty("url") String url,
    @JsonProperty("description") String description,
    @JsonProperty("tgChatIds") List<Integer> tgChatIds
) {
}
