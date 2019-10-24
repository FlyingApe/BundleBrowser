package nl.bastiaansierd.bundleb.logic.objects;

import nl.bastiaansierd.bundleb.interfaces.logic.objects.Header;
import nl.bastiaansierd.bundleb.logic.helpers.MarkdownParser;

public class MarkdownPage extends Page {
    private String rawMarkdown;

    public MarkdownPage(BundelBHeader header){
        super(header);
        loadRawMarkdown();
        setHtmlTekst(parseMarkdownToHtml());
    }

    public void setRawMarkdown(String rawMarkdown){
        this.rawMarkdown = rawMarkdown;
    }

    public String getRawMarkdown() {
        return rawMarkdown;
    }

    private void loadRawMarkdown(){ // data method
        Header PageHeader = getHeader();
        String lokatie = PageHeader.getAddress();

        // hier komt code om opgeslagen markdown van 'lokatie' in dit object te laden

        this.rawMarkdown = "";

    }

    private String parseMarkdownToHtml(){ // logic method
        String markdown = this.rawMarkdown;
        String html = MarkdownParser.parse(markdown);
        return html;
    }

    public void reloadToHtml(){
        setHtmlTekst(parseMarkdownToHtml());
    }

}
