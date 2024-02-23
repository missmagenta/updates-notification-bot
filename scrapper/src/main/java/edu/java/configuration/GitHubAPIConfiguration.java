package edu.java.configuration;

import org.springframework.http.MediaType;

public interface GitHubAPIConfiguration {
    String API_BASE_URL = "https://api.github.com/";
    String API_VERSION_SPEC = "application/vnd.github.v3+json";
    String JSON_CONTENT_TYPE = MediaType.APPLICATION_JSON_VALUE;
}
