package nl.bastiaansierd.bundleb.ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import nl.bastiaansierd.bundleb.interfaces.logic.BundelLogic;
import nl.bastiaansierd.bundleb.interfaces.logic.ObjectGenerator;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.Bundel;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.BundelLeaf;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.Header;
import nl.bastiaansierd.bundleb.logic.BundelBEnvironment;
import nl.bastiaansierd.bundleb.ui.models.BundelTreeView;

import java.util.ArrayList;
import java.util.Collections;

public class PageAddController {
    private Bundel selectedBundel;
    private BundelTreeView treeViewModel;
    private ArrayList<String> itemPathList;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TreeView<BundelLeaf> categorieTree;
    @FXML
    private Label selectedParent;
    @FXML
    private TextField pageName;
    @FXML
    private TextField categoryAddress;


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

        selectedParent.setText(itemPath);
    }

    public void createNewPage(ActionEvent actionEvent) {
        ObjectGenerator gen = BundelBEnvironment.getInstance().getObjectGenerator();
        BundelLogic bundelLogic = gen.getBundelLogic(selectedBundel.getBundelPath());
        Header pageHeader = gen.getHeader(pageName.getText(), categoryAddress.getText());

        bundelLogic.addPage(itemPathList, pageHeader);

        Stage stage = (Stage) rootPane.getScene().getWindow();

        stage.hide();
    }


}
