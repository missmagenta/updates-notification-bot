package edu.java.configuration.client;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

@Configuration
public class StackOverFlowAPIConfiguration {
    public static final String API_BASE_URL = "https://api.stackexchange.com/2.3";
    public static final String API_VERSION_SPEC = "2.3";
    public static final  String JSON_CONTENT_TYPE = MediaType.APPLICATION_JSON_VALUE;
}
