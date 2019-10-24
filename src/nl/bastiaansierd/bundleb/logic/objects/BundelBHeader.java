package nl.bastiaansierd.bundleb.logic.objects;


import nl.bastiaansierd.bundleb.enums.LeafType;
import nl.bastiaansierd.bundleb.enums.PageType;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.Header;

import java.io.Serializable;

public class BundelBHeader implements Header, Serializable {
    private LeafType leafType = LeafType.PAGE;

    private String name;
    private String address;
    private PageType pageType = PageType.HTML;
    private Boolean locallyStored = false;

    public BundelBHeader(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public void setPageType(PageType pageType) {
        this.pageType = pageType;
    }

    public void setLocallyStored(Boolean locallyStored) {
        this.locallyStored = locallyStored;
    }

    public LeafType getLeafType() {
        return leafType;
    }

    public PageType getPageType(){ return pageType; }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Boolean isLocallyStored(){
        return locallyStored;
    }

    @Override
    public String toString(){
        return getLeafType().toString() + ": " + getName() + "\n";
    }
}
