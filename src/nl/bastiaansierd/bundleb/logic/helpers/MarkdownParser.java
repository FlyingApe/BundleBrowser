package nl.bastiaansierd.bundleb.logic.helpers;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataSet;


public class MarkdownParser {
    public static String parse(String markdown){
        String htmlString = null;

/*
de volgende code komt deels van https://github.com/vsch/flexmark-java
 */

        MutableDataSet options = new MutableDataSet();

        // uncomment to set optional extensions
        //options.set(Parser.EXTENSIONS, Arrays.asList(TablesExtension.create(), StrikethroughExtension.create()));

        // uncomment to convert soft-breaks to hard breaks
        options.set(HtmlRenderer.SOFT_BREAK, "<br />");

        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();

        // You can re-use parser and renderer instances
        Node document = parser.parse(markdown);
        htmlString = renderer.render(document);

/*
tot hier gekopieerde code van https://github.com/vsch/flexmark-java
*/
        return htmlString;
    }
}
