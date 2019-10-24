package nl.bastiaansierd.bundleb.logic.objects;

import nl.bastiaansierd.bundleb.enums.LeafType;
import nl.bastiaansierd.bundleb.enums.PageType;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.Header;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BundelBHeaderTest {
    Header header = new BundelBHeader("Test Header", "C:\\fakepath");

    @Test
    void setPageType() {
        header.setPageType(PageType.HTML);
        assertEquals(PageType.HTML, header.getPageType());
    }

    @Test
    void getLeafType() {
        assertEquals(LeafType.PAGE, header.getLeafType());
    }

    @Test
    void getPageType() {
        header.setPageType(PageType.HTML);
        assertEquals(PageType.HTML, header.getPageType());
    }

    @Test
    void getNaam() {
        assertEquals("Test Header", header.getName());
    }

    @Test
    void getLokatie() {
        assertEquals("C:\\fakepath", header.getAddress());
    }
}