package edu.java.configuration;

import org.springframework.http.MediaType;

public interface StackOverFlowAPIConfiguration {
    String API_BASE_URL = "https://api.stackexchange.com/";
    String API_VERSION_SPEC = "2.3";
    String JSON_CONTENT_TYPE = MediaType.APPLICATION_JSON_VALUE;
}
