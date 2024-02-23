package edu.java.linkpraser.parser;

import edu.java.linkpraser.enums.Host;
import edu.java.linkpraser.parsingresult.ParsingResult;
import edu.java.linkpraser.parsingresult.StackOverFlowParsingResult;

public class StackOverFlowLinkParser implements LinkParser {

    @Override
    public ParsingResult parse(String linkPath) {
        if (linkPath != null && linkPath.startsWith("/questions/")) {
            linkPath = linkPath.substring("/questions/".length());

            int slashIndex = linkPath.indexOf("/");
            String questionId = linkPath.substring(0, slashIndex);
            return new StackOverFlowParsingResult(questionId);
        }
        return null;
    }

    @Override
    public boolean supportsHost(String host) {
        return host.equals(Host.STACKOVERFLOW_HOST.getValue());
    }
}
