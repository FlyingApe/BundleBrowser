package nl.bastiaansierd.bundleb.logic.objects;

import nl.bastiaansierd.bundleb.interfaces.logic.objects.Header;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.Pagina;

public abstract class Page implements Pagina {
    private Header header;
    private String htmlTekst;

    //
    public Page(Header header) {
        this.header = header;
    }

    //getters
    public Header getHeader(){
        return this.header;
    }

    public String getHtml(){
        return this.htmlTekst;
    }

    //setters
    protected void setHtmlTekst(String htmlTekst) {
        this.htmlTekst = htmlTekst;
    }

}

