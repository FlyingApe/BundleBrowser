package nl.bastiaansierd.bundleb.logic;

import nl.bastiaansierd.bundleb.interfaces.data.BundelData;
import nl.bastiaansierd.bundleb.interfaces.data.DataRouter;
import nl.bastiaansierd.bundleb.interfaces.logic.BundelLogic;
import nl.bastiaansierd.bundleb.interfaces.logic.ObjectGenerator;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.Bundel;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.Category;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.Header;
import nl.bastiaansierd.bundleb.logic.helpers.XMLBundelTreeParser;
import org.w3c.dom.Document;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class BundelBBundelLogic implements BundelLogic {
    private DataRouter dataRouter = BundelBEnvironment.getInstance().getDataRouter();
    private ObjectGenerator gen = BundelBEnvironment.getInstance().getObjectGenerator();
    private BundelData data = dataRouter.getBundelData();
    private String directoryPath;

    public BundelBBundelLogic(String directoryPath) {
        this.directoryPath = directoryPath;
        data.setBundelPath(directoryPath);
    }

    public boolean doesBundelExist() {
        return data.folderIsBundel(directoryPath);
    }

    public void createBundel(){
        data.createNewBundel(directoryPath);
    }

    public Bundel getBundel(){
        Path path = Paths.get(directoryPath);
        String dirName = path.getFileName().toString();

        Document doc = data.getBundelTreeDoc();
        XMLBundelTreeParser treeParser = new XMLBundelTreeParser(doc);

        Category tree = treeParser.getTree();

        return gen.getBundel(dirName, directoryPath, tree);
    }

    public void addCategory(ArrayList<String> itemPathList, String categoryName){
        data.addCategory(itemPathList, categoryName);
    }

    public void addPage(ArrayList<String> itemPathList, Header pageHeader){
        data.addPage(itemPathList, pageHeader);
    }
}
