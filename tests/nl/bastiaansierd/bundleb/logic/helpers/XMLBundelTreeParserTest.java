package nl.bastiaansierd.bundleb.logic.helpers;

import nl.bastiaansierd.bundleb.interfaces.logic.objects.Categorie;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.Header;
import nl.bastiaansierd.bundleb.logic.objects.BundelBCategorie;
import nl.bastiaansierd.bundleb.logic.objects.BundelBHeader;
import nl.bastiaansierd.datalogger.logic.Logger;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class XMLBundelTreeParserTest {

    @Test
    void getTree() {
        Document doc = createTestDoc();
        XMLBundelTreeParser parser = new XMLBundelTreeParser(doc);

        Categorie root = parser.getTree();
        Categorie shouldBeLike = createTestCategorie();

        assertEquals(shouldBeLike.toString(), root.toString());
    }

    private Document createTestDoc(){
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-16\"?><categorie><naam>Root</naam><categorie><naam>TestCategorie</naam><pagina type=\"html\"><naam>Test Pagina</naam><lokatie>https://www.test.nl</lokatie></pagina><categorie><naam>Test-Sub-Categorie</naam><pagina type=\"html\"><naam>Test Pagina Sub-Categorie</naam><lokatie>https://www.test.nl</lokatie></pagina></categorie></categorie></categorie>";
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

    private Categorie createTestCategorie(){
        Categorie root = new BundelBCategorie("Root");
        Categorie subCat = new BundelBCategorie("Test-Sub-Categorie");
        Header testSubHead = new BundelBHeader("Test Pagina Sub-Categorie","https://www.test.nl");
        subCat.addChild(testSubHead);
        Categorie testCat = new BundelBCategorie("TestCategorie");
        Header testHead = new BundelBHeader("Test Pagina","https://www.test.nl");
        testCat.addChild(testHead);
        testCat.addChild(subCat);
        root.addChild(testCat);

        return root;
    }
}