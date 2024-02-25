package edu.java.client.github.dto.records;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

public record UserInformationResponse(
    @JsonProperty("login") String username
) implements Serializable {
}
