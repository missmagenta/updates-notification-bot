package edu.java.linkpraser.parser;

import edu.java.linkpraser.parsingresult.ParsingResult;
import edu.java.linkpraser.parsingresult.StackOverFlowParsingResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StackOverFlowLinkParser implements LinkParser {
    private static final Pattern PATTERN = Pattern.compile(
        "(?:https?://)?stackoverflow\\.com/questions/(?<id>\\d+)(?:/.*)?$");


    @Override
    public ParsingResult parse(String link) {
        Matcher matcher = PATTERN.matcher(link);
        if (matcher.matches()) {
            return new StackOverFlowParsingResult(matcher.group("id"));
        } else {
            return null;
        }
    }
}
