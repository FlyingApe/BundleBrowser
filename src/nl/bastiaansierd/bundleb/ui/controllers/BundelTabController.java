package nl.bastiaansierd.bundleb.ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.Bundel;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.BundelLeaf;
import nl.bastiaansierd.bundleb.ui.models.BundelTreeView;

import java.net.URL;
import java.util.ResourceBundle;

public class BundelTabController implements Initializable {
    private Bundel bundel;

    @FXML
    private TreeView<BundelLeaf> bundelTreeView;
    @FXML
    private WebView bundelPageView;

    public BundelTabController(Bundel bundel){
        this.bundel = bundel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        WebEngine webEngine = bundelPageView.getEngine();
        webEngine.loadContent("<h1>"+bundel.getBundelPath()+"</h1>");
        BundelTreeView bundelTreeViewModel = new BundelTreeView(bundel.getTree(), bundelTreeView, bundelPageView);
        bundelTreeView = bundelTreeViewModel.getTreeView();
        bundelTreeView.refresh();
    }

    public void setBundel(Bundel bundel){
        this.bundel = bundel;
    }
}
