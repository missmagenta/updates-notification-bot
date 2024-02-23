package edu.java.linkpraser.parser;

import edu.java.linkpraser.parsingresult.ParsingResult;
import lombok.RequiredArgsConstructor;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class LinkParserHandler {
    final List<LinkParser> parsers;

    public ParsingResult checkLink(String link) {
        try {
            URL url = new URL(link);
            url.toURI();
            String host = url.getHost();

            for (LinkParser parser : parsers) {
                if (parser.supportsHost(host)) {
                    ParsingResult parsingResult = parser.parse(url.getPath());
                    if (parsingResult != null) {
                        return parsingResult;
                    }
                }
            }
        } catch (MalformedURLException | URISyntaxException e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
