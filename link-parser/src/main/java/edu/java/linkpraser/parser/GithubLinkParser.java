package edu.java.linkpraser.parser;

import edu.java.linkpraser.enums.Host;
import edu.java.linkpraser.parsingresult.GithubParsingResult;
import edu.java.linkpraser.parsingresult.ParsingResult;

public class GithubLinkParser implements LinkParser {
    @Override
    public ParsingResult parse(String linkPath) {
        if (linkPath != null && linkPath.startsWith("/")) {
            linkPath = linkPath.substring(1);
            String[] linkParts = linkPath.split("/");

            if (linkParts.length >= 2) {
                String username = linkParts[0];
                String repository = linkParts[1];

                return new GithubParsingResult(username, repository);
            }
        }
        return null;
    }

    @Override
    public boolean supportsHost(String host) {
        return host.equals(Host.GITHUB_HOST.getValue());
    }
}
