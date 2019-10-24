package nl.bastiaansierd.bundleb.logic.objects;

import nl.bastiaansierd.bundleb.enums.LeafType;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.Bundel;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.BundelLeaf;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.Categorie;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BundelBCategorieTest {
    Categorie categorie = new BundelBCategorie("tester");

    @Test
    void addChild() {
        BundelLeaf subCat = new BundelBCategorie("testSub");
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

        BundelLeaf subCat = new BundelBCategorie("testSub");
        categorie.addChild(subCat);

        assertEquals(1,categorie.getChildren().size());
    }
}