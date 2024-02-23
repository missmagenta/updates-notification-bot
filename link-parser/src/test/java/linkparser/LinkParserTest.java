package linkparser;

import edu.java.linkpraser.parser.GithubLinkParser;
import edu.java.linkpraser.parser.LinkParserHandler;
import edu.java.linkpraser.parser.StackOverFlowLinkParser;
import edu.java.linkpraser.parsingresult.GithubParsingResult;
import edu.java.linkpraser.parsingresult.ParsingResult;
import edu.java.linkpraser.parsingresult.StackOverFlowParsingResult;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class LinkParserTest {

    @Test
    void testGithubLinks() {
        final LinkParserHandler linkParserHandler = createLinkParsers();
        final String correctLink1 = "https://github.com/chemistry-lab/chemistry-lab-vr";
        final String correctLink2 = "https://github.com/sanyarnd/tinkoff-java-course-2023/";
        final ParsingResult parsingResult1 = linkParserHandler.checkLink(correctLink1);
        final ParsingResult parsingResult2 = linkParserHandler.checkLink(correctLink2);

        assertEquals(new GithubParsingResult("chemistry-lab", "chemistry-lab-vr"), parsingResult1);
        assertEquals(new GithubParsingResult("sanyarnd", "tinkoff-java-course-2023"), parsingResult2);
    }

    @Test
    void testStackOverFlowLinks() {
        final LinkParserHandler linkParserHandler = createLinkParsers();
        final String correctLink1 = "https://stackoverflow.com/questions/11227809/why-is-processing-a-sorted-array-faster-than-processing-an-unsorted-array";
        final String correctLink2 = "https://stackoverflow.com/questions/8318911/why-does-html-think-chucknorris-is-a-color";
        final ParsingResult parsingResult1 = linkParserHandler.checkLink(correctLink1);
        final ParsingResult parsingResult2 = linkParserHandler.checkLink(correctLink2);

        assertEquals(new StackOverFlowParsingResult("11227809"), parsingResult1);
        assertEquals(new StackOverFlowParsingResult("8318911"), parsingResult2);
    }



    private LinkParserHandler createLinkParsers() {
        return new LinkParserHandler(List.of(new GithubLinkParser(), new StackOverFlowLinkParser()));
    }
}
