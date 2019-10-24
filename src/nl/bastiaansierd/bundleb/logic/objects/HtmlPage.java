package nl.bastiaansierd.bundleb.logic.objects;

import nl.bastiaansierd.bundleb.interfaces.logic.objects.Header;

public class HtmlPage extends Page {
    public HtmlPage(BundelBHeader header){
        super(header);
        loadHtml();
    }

    private void loadHtml(){ // data method
        Header PageHeader = getHeader();
        String lokatie = PageHeader.getAddress();

        // hier komt code die html van 'lokatie' in het object laad

        setHtmlTekst("<h1>test</h1>");
    }
}
