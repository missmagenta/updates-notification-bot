package edu.java.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LinkResponse(
    @JsonProperty("id") int id,
    @JsonProperty("url") String url
) {
}
