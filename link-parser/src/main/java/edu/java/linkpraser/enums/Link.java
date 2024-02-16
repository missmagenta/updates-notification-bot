package edu.java.linkpraser.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Link {

    GITHUB_LINK("github.com"),
    STACKOVERFLOW_LINK("stackoverflow.com");

    private final String value;

    public String getValue() {
        return value;
    }
}
