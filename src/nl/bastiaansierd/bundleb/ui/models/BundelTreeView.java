package nl.bastiaansierd.bundleb.ui.models;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.web.WebView;
import nl.bastiaansierd.bundleb.enums.LeafType;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.BundelLeaf;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.Category;
import nl.bastiaansierd.bundleb.logic.objects.BundelBHeader;
import nl.bastiaansierd.bundleb.logic.objects.BundelTreeEditHolder;

import java.util.ArrayList;

public class BundelTreeView {
    private TreeView<BundelLeaf> treeView;
    private Category rootCategorie;
    private WebView pageView = null;
    private boolean showRoot = false;


    //constructor voor controllers met een een WebView, pagina's moeten in de tree weergegeven worden en de WebView updaten
    public BundelTreeView(Category rootCategorie, TreeView<BundelLeaf> treeView, WebView pageView) {
        this.rootCategorie = rootCategorie;
        this.treeView = treeView;
        this.pageView = pageView;
        setTreeView();
        BundelTreeEditHolder.init();
    }

    //constructor voor controllers zonder WebView, pagina's hoeven niet in de tree te verschijnen, alleen categoriën
    public BundelTreeView(Category rootCategorie, TreeView<BundelLeaf> treeView) {
        this.rootCategorie = rootCategorie;
        this.treeView = treeView;
        showRoot = true;
        setTreeView();
    }


    public TreeView getTreeView() {
        return treeView;
    }

    public String getSelectedItemPath(){
        return null;
    }

    public void setTreeView(){
        TreeItem<BundelLeaf> rootItem = buildBranch(rootCategorie);
        rootItem.setExpanded(true);

        treeView.setCellFactory(p -> new CellFactory(BundelTreeView.this));

        treeView.setRoot(rootItem);
        if(!showRoot){
            treeView.setShowRoot(false);
        }
        treeView.refresh();
    }


    //TreeItem Creator
    private TreeItem<BundelLeaf> buildBranch(Category category){
        TreeItem<BundelLeaf> branch = new TreeItem<>(category);

        TreeItem<BundelLeaf> childPrePlaceHolder = new TreeItem<>(new PlaceHolder(category, 0));
        branch.getChildren().add(childPrePlaceHolder);

        //itterates through categories and add's pages to them
        BundelLeaf childLeaf;
        int index=0;
        for (BundelLeaf leaf : category.getChildren()){
            index++;
            if(leaf.getLeafType()== LeafType.CATEGORY){
                TreeItem<BundelLeaf> child = buildBranch((Category)leaf);
                child.setExpanded(true);
                branch.getChildren().add(child);
            } else if (leaf.getLeafType() == LeafType.PAGE && pageView != null) {
                childLeaf = leaf;
                ((BundelBHeader) childLeaf).setParent(category);
                TreeItem<BundelLeaf> child = new TreeItem<>(childLeaf);
                branch.getChildren().add(child);
            } else {
                //Logger.log("BundelTabController.buildBranch", "leaf heeft geen LeafType: " + leaf.getName());
            }
            TreeItem<BundelLeaf> childPlaceHolder = new TreeItem<>(new PlaceHolder(category, index));
            branch.getChildren().add(childPlaceHolder);
        }

        return branch;
    }


    public ArrayList<String> getItemPath(TreeItem<BundelLeaf> treeItem, ArrayList<String> itemPathList){
        ArrayList<String> returnObject = new ArrayList<>();

        if(treeItem == null){
            treeItem = treeView.getSelectionModel().getSelectedItem();
        }


        String newPath = null;
        try{
            String treeItemName = treeItem.getValue().getName();
            itemPathList.add(treeItemName);
            if(treeItem.getParent() != null){
                String parentPath = getItemPath(treeItem.getParent(), itemPathList).get(0);
                if(parentPath != null){
                    newPath = parentPath + "\\" + treeItemName;
                } else {
                    newPath = treeItemName;
                }
            } else {
                newPath = treeItemName;
            }
        } catch (Exception e){

        }

        returnObject.add(newPath);
        returnObject.addAll(itemPathList);

        return returnObject;
    }

}
