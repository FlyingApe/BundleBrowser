package nl.bastiaansierd.bundleb.ui.models;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Callback;
import nl.bastiaansierd.bundleb.Main;
import nl.bastiaansierd.bundleb.enums.LeafType;
import nl.bastiaansierd.bundleb.enums.PageType;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.BundelLeaf;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.Categorie;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.Header;
import nl.bastiaansierd.datalogger.logic.Logger;

import java.util.ArrayList;
import java.util.Collections;

public class BundelTreeView {
    private TreeView<BundelLeaf> treeView;
    private Categorie rootCategorie;
    private WebView pageView = null;
    private boolean showRoot = false;

    //constructor voor controllers met een een WebView, pagina's moeten in de tree weergegeven worden en de WebView updaten
    public BundelTreeView(Categorie rootCategorie, TreeView<BundelLeaf> treeView, WebView pageView) {
        this.rootCategorie = rootCategorie;
        this.treeView = treeView;
        this.pageView = pageView;
        setTreeView();
    }

    //constructor voor controllers zonder WebView, pagina's hoeven niet in de tree te verschijnen, alleen categoriën
    public BundelTreeView(Categorie rootCategorie, TreeView<BundelLeaf> treeView) {
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

    private void setTreeView(){
        TreeItem<BundelLeaf> rootItem = buildBranch(rootCategorie);
        rootItem.setExpanded(true);

        treeView.setCellFactory(new Callback<TreeView<BundelLeaf>, TreeCell<BundelLeaf>>(){
            @Override
            public TreeCell<BundelLeaf> call(TreeView<BundelLeaf> p) {
                return new CellFactory();
            }
        });

        treeView.setRoot(rootItem);
        if(!showRoot){
            treeView.setShowRoot(false);
        }
        treeView.refresh();
    }


    //TreeItem Creator
    private TreeItem<BundelLeaf> buildBranch(Categorie categorie){
        TreeItem<BundelLeaf> branch = new TreeItem<>(categorie);

        BundelLeaf childLeaf;
        for (BundelLeaf leaf : categorie.getChildren()){
            if(leaf.getLeafType()== LeafType.CATEGORY){
                TreeItem<BundelLeaf> child = buildBranch((Categorie)leaf);
                child.setExpanded(true);
                branch.getChildren().add(child);
            } else if (leaf.getLeafType() == LeafType.PAGE && pageView != null) {
                childLeaf = leaf;
                TreeItem<BundelLeaf> child = new TreeItem<>(childLeaf);
                branch.getChildren().add(child);
            } else {
                //Logger.log("BundelTabController.buildBranch", "leaf heeft geen LeafType: " + leaf.getName());
            }
        }

        return branch;
    }


    // de CellFactory voor de TreeView
    private final class CellFactory extends TreeCell<BundelLeaf> {

        @Override
        protected void updateItem(BundelLeaf leaf, boolean empty){
            super.updateItem(leaf, empty);
            if (!empty && leaf != null) {
                setText(leaf.getName());
                if(leaf.getLeafType() == LeafType.CATEGORY){

                    setGraphic(new ImageView(new Image(Main.class.getResourceAsStream("images/bullet.png"))));
                }
                else if (leaf.getLeafType() == LeafType.PAGE){
                    setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            //leaf omzetten naar header, zodat we de PageType kunnen achterhalen
                            Header header = (Header) leaf;
                            WebEngine webEngine = pageView.getEngine();
                            if(header.getPageType() == PageType.HTML){
                                try {
                                    webEngine.load(header.getAddress());
                                } catch (Exception e) {
                                    Logger.log("BundelTreeView.CellFactory.updateItem",e.getMessage());
                                }
                            }
                        }
                    });
                }
            } else {
                setText(null);
                setGraphic(null);
            }

        }
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
