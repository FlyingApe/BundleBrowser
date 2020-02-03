package nl.bastiaansierd.bundleb.logic.helpers;

import nl.bastiaansierd.bundleb.enums.PageType;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.Category;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.Header;
import nl.bastiaansierd.bundleb.logic.BundelBEnvironment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLBundelTreeParser {
    private Category tree;

    public XMLBundelTreeParser(Document doc) {
        Element docElement = doc.getDocumentElement();
        tree = getBranch(docElement, true);
    }

    public Category getTree(){
        return tree;
    }

    private Category getBranch(Element element, boolean isRoot){
        Category branch = BundelBEnvironment.getInstance().getObjectGenerator().getCategorie(element.getAttribute("name"));
//        Category branch = BundelBEnvironment.getInstance().getObjectGenerator().getCategorie(element.getTagName());

        NodeList nodeList = element.getChildNodes();
        int length = nodeList.getLength();
        for(int i = 0; i < length; i++) {
            if(nodeList.item(i).getNodeType() == Node.ELEMENT_NODE){
                Element child = (Element) nodeList.item(i);
                if(child.getTagName() == "category"){
                    branch.addChild(getBranch(child, false));
                } else if (child.getTagName() == "page"){
                    String pageType = child.getAttribute("type");
                    String naam = child.getAttribute("name");
                    String lokatie = child.getAttribute("address");
                    Header header = BundelBEnvironment.getInstance().getObjectGenerator().getHeader(naam, lokatie);
                    if (pageType == "html") {
                        header.setPageType(PageType.HTML);
                    }
                    branch.addChild(header);
                }
            }
        }
        return branch;
    }
}
