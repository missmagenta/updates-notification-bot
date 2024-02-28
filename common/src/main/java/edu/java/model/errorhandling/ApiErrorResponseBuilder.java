package edu.java.model.errorhandling;

import edu.java.model.errorhandling.dto.ApiErrorResponse;

import java.util.Arrays;

public class ApiErrorResponseBuilder {

    public static ApiErrorResponse buildErrorResponse(String statusCode, Exception e) {
        return new ApiErrorResponse(
            e.getLocalizedMessage(),
            statusCode,
            e.getClass().getName(),
            e.getMessage(),
            Arrays.stream(e.getStackTrace()).map(StackTraceElement::toString).toList());
    }
}
