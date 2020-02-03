package nl.bastiaansierd.bundleb.logic.objects;

import nl.bastiaansierd.bundleb.interfaces.logic.objects.Bundel;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BundelBBundelTest {
    private Bundel bundel = new BundelBBundel("Test", "C:\\fakepath", new BundelBCategory("testCat"));

    @Test
    void getNaam() {
        String naam = bundel.getNaam();
        assertEquals("Test", naam);
    }

    @Test
    void getBundelPath() {
        String path = bundel.getBundelPath();
        assertEquals("C:\\fakepath", path);
    }

    @Test
    void getTree() {
        Category root = bundel.getTree();
        Category shouldBeLike = (Category) new BundelBCategory("testCat");

        assertEquals(shouldBeLike.toString(), root.toString());
    }
}