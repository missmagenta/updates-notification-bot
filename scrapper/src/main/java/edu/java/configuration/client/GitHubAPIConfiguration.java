package edu.java.configuration.client;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

@Configuration
public class GitHubAPIConfiguration {
    public static final String API_BASE_URL = "https://api.github.com/";
    public static final String API_VERSION_SPEC = "application/vnd.github.v3+json";
    public static final String JSON_CONTENT_TYPE = MediaType.APPLICATION_JSON_VALUE;
}
