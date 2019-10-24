package nl.bastiaansierd.bundleb.logic.objects;

import nl.bastiaansierd.bundleb.interfaces.logic.objects.Bundel;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.Categorie;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BundelBBundelTest {
    private Bundel bundel = new BundelBBundel("Test", "C:\\fakepath", new BundelBCategorie("testCat"));

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
        Categorie root = bundel.getTree();
        Categorie shouldBeLike = (Categorie) new BundelBCategorie("testCat");

        assertEquals(shouldBeLike.toString(), root.toString());
    }
}