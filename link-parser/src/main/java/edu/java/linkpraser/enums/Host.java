package edu.java.linkpraser.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Host {

    GITHUB_HOST("github.com"),
    STACKOVERFLOW_HOST("stackoverflow.com");

    private final String value;
}
