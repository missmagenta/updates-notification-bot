package edu.java.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record ListLinkResponse(
    @JsonProperty("links") List<LinkResponse> linkResponses,
    int size
) {
}
