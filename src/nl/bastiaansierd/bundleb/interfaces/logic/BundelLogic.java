package nl.bastiaansierd.bundleb.interfaces.logic;

import nl.bastiaansierd.bundleb.interfaces.logic.objects.Bundel;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.Header;

import java.util.ArrayList;

public interface BundelLogic {
    boolean doesBundelExist();

    void createBundel();

    Bundel getBundel();

    void addCategory(ArrayList<String> itemPathList, String categoryName);

    void addPage(ArrayList<String> itemPathList, Header pageHeader);
}
