package edu.java.linkpraser.parser;

import edu.java.linkpraser.parsingresult.GithubParsingResult;
import edu.java.linkpraser.parsingresult.ParsingResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GithubLinkParser implements LinkParser {
    private static final String USERNAME_REGEX =  "[a-zA-Z\\\\d](?:[a-zA-Z\\\\d]|-(?=[a-zA-Z\\\\d])){1,38}";
    private static final String REPOSITORY_REGEX = "[a-zA-Z\\d._\\-]";
    private static final Pattern PATTERN = Pattern.compile(
        "(?:https?://)?github\\.com" +
            "/" +
            "(?<username>" + USERNAME_REGEX + ")" +
            "/" +
            "(?<repository>" + REPOSITORY_REGEX + "+)/?$");


    @Override
    public ParsingResult parse(String link) {
        Matcher matcher = PATTERN.matcher(link);
        if (matcher.matches()) {
            return new GithubParsingResult(matcher.group("username"), matcher.group("repository"));
        } else {
            return null;
        }
    }
}
