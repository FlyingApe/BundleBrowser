package nl.bastiaansierd.bundleb.logic.objects;

import nl.bastiaansierd.bundleb.enums.LeafType;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.BundelLeaf;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BundelBCategorieTest {
    Category categorie = new BundelBCategory("tester");

    @Test
    void addChild() {
        BundelLeaf subCat = new BundelBCategory("testSub");
        categorie.addChild(subCat);

        assertNotNull(categorie.getChildren());
    }

    @Test
    void getLeafType() {
        assertEquals(LeafType.CATEGORY, categorie.getLeafType());
    }

    @Test
    void getNaam() {
        assertEquals("tester", categorie.getName());
    }

    @Test
    void getChildren() {
        assertEquals(0, categorie.getChildren().size());

        BundelLeaf subCat = new BundelBCategory("testSub");
        categorie.addChild(subCat);

        assertEquals(1,categorie.getChildren().size());
    }
}