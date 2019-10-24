package nl.bastiaansierd.bundleb.ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import nl.bastiaansierd.bundleb.interfaces.logic.BundelLogic;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.Bundel;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.BundelLeaf;
import nl.bastiaansierd.bundleb.logic.BundelBEnvironment;
import nl.bastiaansierd.bundleb.ui.models.BundelTreeView;

import java.util.ArrayList;
import java.util.Collections;

public class CategorieAddController {
    private Bundel selectedBundel;
    private BundelTreeView treeViewModel;
    private ArrayList<String> itemPathList;

    @FXML
    private TreeView<BundelLeaf> categorieTree;
    @FXML
    private Label selectedCategorie;
    @FXML
    private TextField categoryName;
    @FXML
    private HBox rootPane;

    @FXML
    private void initialize(){
        BundelBEnvironment env = BundelBEnvironment.getInstance();
        selectedBundel = env.getSelectedBundel();
        treeViewModel = new BundelTreeView(selectedBundel.getTree(), categorieTree);
        categorieTree = treeViewModel.getTreeView();
    }

    //Event Handlers
    @FXML
    private void checkSelected(){
        //itemPathList initialiseren en vervolgens aanmaken
        itemPathList = treeViewModel.getItemPath(null, new ArrayList<>());

        //String volledige string uit het object halen
        String itemPath = itemPathList.remove(0);

        //itemPathList wordt achterstevoren aangemaakt, dus moet omgedraait worden
        Collections.reverse(itemPathList);

        selectedCategorie.setText(itemPath);
        //Logger.log("CategorieAddController.checkSelected", "itemPath: " + itemPath);
    }

    public void createNewCat(ActionEvent actionEvent) {
        BundelLogic bundelLogic = BundelBEnvironment.getInstance().getObjectGenerator().getBundelLogic(selectedBundel.getBundelPath());
        bundelLogic.addCategory(itemPathList, categoryName.getText());

        Stage stage = (Stage) rootPane.getScene().getWindow();

        stage.hide();
    }
}
