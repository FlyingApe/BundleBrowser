package nl.bastiaansierd.bundleb.interfaces.data;

import nl.bastiaansierd.bundleb.interfaces.logic.objects.Header;
import org.w3c.dom.Document;

import java.util.ArrayList;

public interface BundelData {
    boolean folderIsBundel(String path);

    void createNewBundel(String path);

    Document getBundelTreeDoc();

    void addCategory(ArrayList<String> itemPathList, String categoryName);

    void setBundelPath(String bundelPath);

    void addPage(ArrayList<String> itemPathList, Header pageHeader);
}
