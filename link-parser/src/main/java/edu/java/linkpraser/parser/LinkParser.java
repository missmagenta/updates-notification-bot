package edu.java.linkpraser.parser;

import edu.java.linkpraser.parsingresult.ParsingResult;


public interface LinkParser {
    ParsingResult parse(String link);
}
