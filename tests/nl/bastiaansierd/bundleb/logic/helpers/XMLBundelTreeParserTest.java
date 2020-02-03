package nl.bastiaansierd.bundleb.logic.helpers;

import nl.bastiaansierd.bundleb.interfaces.logic.objects.Category;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.Header;
import nl.bastiaansierd.bundleb.logic.objects.BundelBCategory;
import nl.bastiaansierd.bundleb.logic.objects.BundelBHeader;
import nl.bastiaansierd.datalogger.logic.Logger;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class XMLBundelTreeParserTest {
/*
    @Test
    void getTree() {
        Document doc = createTestDoc();
        XMLBundelTreeParser parser = new XMLBundelTreeParser(doc);

        Category root = parser.getTree();
        Category shouldBeLike = createTestCategorie();

        assertEquals(shouldBeLike.toString(), root.toString());
    }

    private Document createTestDoc(){
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-16\"?><categorie><naam>Root</naam><categorie><naam>TestCategorie</naam><pagina type=\"html\"><naam>Test Pagina</naam><lokatie>https://www.test.nl</lokatie></pagina><categorie><naam>Test-Sub-Category</naam><pagina type=\"html\"><naam>Test Pagina Sub-Category</naam><lokatie>https://www.test.nl</lokatie></pagina></categorie></categorie></categorie>";
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        Document doc = null;
        try {
            builder = factory.newDocumentBuilder();
            doc = builder.parse(new ByteArrayInputStream(xml.getBytes("UTF-16")));
        }
        catch (Exception e){
            Logger.log("XMLBundelTreeParserTest.createTestDoc", e.getMessage());
        }

        return doc;
    }
/*
    private Category createTestCategorie(){
        Category root = new BundelBCategory("Root");
        Category subCat = new BundelBCategory("Test-Sub-Category");
        Header testSubHead = new BundelBHeader("Test Pagina Sub-Category","https://www.test.nl");
        subCat.addChild(testSubHead);
        Category testCat = new BundelBCategory("TestCategorie");
        Header testHead = new BundelBHeader("Test Pagina","https://www.test.nl");
        testCat.addChild(testHead);
        testCat.addChild(subCat);
        root.addChild(testCat);

        return root;
    }
 */
}
