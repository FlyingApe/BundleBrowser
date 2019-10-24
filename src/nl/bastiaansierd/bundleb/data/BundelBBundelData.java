package nl.bastiaansierd.bundleb.data;

import nl.bastiaansierd.bundleb.data.dataAccessConnectors.BundelFoundCheck;
import nl.bastiaansierd.bundleb.data.dataAccessConnectors.XMLFileCreator;
import nl.bastiaansierd.bundleb.data.dataAccessConnectors.XMLReadWriter;
import nl.bastiaansierd.bundleb.interfaces.data.BundelData;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.Header;
import nl.bastiaansierd.bundleb.logic.BundelBEnvironment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.ArrayList;

public class BundelBBundelData implements BundelData {
    private String bundelPath;

    public void setBundelPath(String bundelPath) {
        this.bundelPath = bundelPath;
    }

    public boolean folderIsBundel(String path){
        BundelFoundCheck foundCheck = new BundelFoundCheck(path);
        return foundCheck.isBundelPresent();
    }

    public void createNewBundel(String path){
        XMLFileCreator xmlFileCreator = new XMLFileCreator();
        xmlFileCreator.setPath(path);
        xmlFileCreator.createFile();
    }

    public Document getBundelTreeDoc(){
        XMLReadWriter readWriter = new XMLReadWriter();
        return readWriter.read(this.bundelPath + "\\tree.xml");
    }

    private Element getParent(ArrayList<String> itemPathList, Document doc){
        Element childElement = doc.getDocumentElement();

        for (String parent: itemPathList) {
            if(childElement.getAttribute("name")==parent){
                continue;
            }
            else{
                NodeList nodeList = childElement.getChildNodes();
                int length = nodeList.getLength();
                for(int i = 0; i < length; i++) {
                    if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                        Element child = (Element) nodeList.item(i);
                        if(child.getAttribute("name").equals(parent)){
                            childElement = child;
                            break;
                        }
                    }
                }
                continue;
            }
        }

        return childElement;
    }

    public void addCategory(ArrayList<String> itemPathList, String categoryName){
        Document bundelDoc = getBundelTreeDoc();

        Element category = bundelDoc.createElement("category");
        category.setAttribute("name", categoryName);

        Element parent = getParent(itemPathList, bundelDoc);
        parent.appendChild(category);

        XMLReadWriter readWriter = new XMLReadWriter();
        readWriter.write(bundelDoc, new File(BundelBEnvironment.getInstance().getSelectedBundel().getBundelPath()+"\\tree.xml"));
    }


    public void addPage(ArrayList<String> itemPathList, Header pageHeader){
        Document bundelDoc = getBundelTreeDoc();

        Element page = bundelDoc.createElement("page");
        page.setAttribute("name", pageHeader.getName());
        page.setAttribute("address", pageHeader.getAddress());
        page.setAttribute("type", pageHeader.getPageType().toString());
        page.setAttribute("local", pageHeader.isLocallyStored().toString());

        Element parent = getParent(itemPathList, bundelDoc);
        parent.appendChild(page);

        XMLReadWriter readWriter = new XMLReadWriter();
        readWriter.write(bundelDoc, new File(BundelBEnvironment.getInstance().getSelectedBundel().getBundelPath()+"\\tree.xml"));
    }

}
