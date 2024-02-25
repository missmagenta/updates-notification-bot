package edu.java.client.github.dto.records;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserInformationResponse(
    @JsonProperty("login") String username
) {
}
