package nl.bastiaansierd.bundleb.interfaces.logic.objects;

import nl.bastiaansierd.bundleb.enums.PageType;

public interface Header extends BundelLeaf {
    void setPageType(PageType pageType);
    PageType getPageType();
    String getName();
    String getAddress();
    Boolean isLocallyStored();
}
