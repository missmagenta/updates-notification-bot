package edu.java.model.errorhandling.dto;

import java.util.List;

public record ApiErrorResponse(
    String description,
    String code,
    String exceptionName,
    String errorMessage,
    List<String> stackTrace
) {
}
