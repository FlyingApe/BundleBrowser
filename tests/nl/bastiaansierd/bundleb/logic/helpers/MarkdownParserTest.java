package nl.bastiaansierd.bundleb.logic.helpers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MarkdownParserTest {

    @Test
    void parse() {
        String markdown = "# Test";
        String html = MarkdownParser.parse(markdown);
        assertEquals("<h1>Test</h1>\n", html);
    }
}