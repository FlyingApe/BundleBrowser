package nl.bastiaansierd.bundleb.data.dataAccessConnectors;

import nl.bastiaansierd.datalogger.logic.Logger;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class XMLReadWriter {
    public Document read(String filePath){
        File file = new File(filePath);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        Document doc = null;
        try {
            builder = factory.newDocumentBuilder();
            doc = builder.parse(file);
        }
        catch (Exception e){
            Logger.log("XMLFileCreator.createNew", e.toString());
        }

        return doc;
    }

    public void write(Document doc, File file){
        //Serializer aanmaken: Horstman, H23.3
        DOMImplementation impl = doc.getImplementation();
        DOMImplementationLS implLS = (DOMImplementationLS) impl.getFeature("LS", "3.0");
        LSSerializer ser = implLS.createLSSerializer();
        ser.getDomConfig().setParameter("format-pretty-print", true);
        String xmlOut = ser.writeToString(doc);

        //Printwriter
        PrintWriter xmlFile = null;
        try {
            xmlFile = new PrintWriter(file.getPath(), StandardCharsets.UTF_16);
        } catch (IOException e) {
            Logger.log("XMLFileCreator.createNew", e.toString());
        }

        xmlFile.println(xmlOut);
        xmlFile.close();
    }
}
