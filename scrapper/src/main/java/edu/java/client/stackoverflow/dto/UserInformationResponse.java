package edu.java.client.stackoverflow.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserInformationResponse(
    @JsonProperty("reputation") int reputation,
    @JsonProperty("accept_rate") int acceptRate,
    @JsonProperty("display_name") String name
) {
}
