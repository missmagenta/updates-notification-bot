package edu.java.dto.response;

import java.util.List;

public record ListLinkResponse(
    List<LinkResponse> linkResponses,
    int size
) {
}
