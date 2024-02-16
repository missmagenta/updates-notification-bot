package edu.java.linkpraser.parsingresult;

public record GithubParsingResult(
    String username,
    String repository
) implements ParsingResult {
}
