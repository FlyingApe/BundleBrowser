package nl.bastiaansierd.bundleb.data.dataAccessConnectors;

import nl.bastiaansierd.datalogger.logic.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class XMLFileCreator {
    private String filePath;

    public void setPath(String filePath) {
        this.filePath = filePath;
    }

    public void createFile() {
        createXmlFile();
    }

    private void createXmlFile(){

        PrintWriter xmlFile = null;
        try {
            xmlFile = new PrintWriter(filePath + "\\tree.xml", StandardCharsets.UTF_16);
        } catch (IOException e) {
            Logger.log("XMLFileCreator.createNew", e.toString());
        }
        xmlFile.println("<?xml version=\"1.0\" encoding=\"UTF-16\"?>\n<root>\n</root>");
        xmlFile.close();
    }
}
