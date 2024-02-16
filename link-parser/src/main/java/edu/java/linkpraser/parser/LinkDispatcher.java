package edu.java.linkpraser.parser;

import edu.java.linkpraser.enums.Link;
import edu.java.linkpraser.parsingresult.ParsingResult;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;


public class LinkDispatcher {
    private final GithubLinkParser githubLinkParser;
    private final StackOverFlowLinkParser stackOverFlowLinkParser;
    public LinkDispatcher(GithubLinkParser githubLinkParser, StackOverFlowLinkParser stackOverFlowLinkParser) {
        this.githubLinkParser = githubLinkParser;
        this.stackOverFlowLinkParser = stackOverFlowLinkParser;
    }

    public ParsingResult dispatchAndParseLink(String link) {
        try {
            URL url = new URL(link);
            url.toURI();
            for (Link enumValue : Link.values()) {
                if (url.getHost().equals(enumValue.getValue())) {
                    switch (enumValue) {
                        case GITHUB_LINK:
                            return githubLinkParser.parse(link);
                        case STACKOVERFLOW_LINK:
                            return stackOverFlowLinkParser.parse(link);
                    }
                }
            }
            return null;
        } catch (URISyntaxException | MalformedURLException e) {
            return null;
        }
    }
}
