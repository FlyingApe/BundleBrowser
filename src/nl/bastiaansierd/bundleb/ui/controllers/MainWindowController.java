package nl.bastiaansierd.bundleb.ui.controllers;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javafx.stage.WindowEvent;
import nl.bastiaansierd.bundleb.interfaces.logic.*;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.Bundel;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.EnvironmentObject;
import nl.bastiaansierd.bundleb.logic.BundelBEnvironment;
import nl.bastiaansierd.bundleb.ui.models.BundelDirectoyChooser;
import nl.bastiaansierd.datalogger.logic.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainWindowController implements SelectedBundelListener{
    private ObjectGenerator gen = BundelBEnvironment.getInstance().getObjectGenerator();
    private String welkomTekst;

    @FXML
    private BorderPane rootPane;
    @FXML
    private TabPane rootTabPane;
    @FXML
    private Tab welkomTab;
    @FXML
    private WebView welkomPage;

    //File menu
    @FXML
    private MenuItem addPagina;
    @FXML
    private MenuItem addCategorie;

    //laden van de omgeving
    @FXML
    private void initialize(){
        //welkomspagina laden
        welkomTab.setUserData(null);
        IntroLogic rootLogic = gen.getIntroLogic();
        welkomTekst = rootLogic.getIntroTekst();
        WebEngine webEngine = welkomPage.getEngine();
        webEngine.loadContent(welkomTekst);

        //deze class toevoegen aan de listeners van BundelBEnvironment,
        //zodat er gereageerd kan worden op het veranderen van de geselecteerde bundel
        BundelBEnvironment env = BundelBEnvironment.getInstance();
        if(!env.listenerListed(this)){
            env.addListener(this);
        }

        //listener voor selected tab
        rootTabPane.getSelectionModel().selectedItemProperty().addListener((obs,ov,nv)->{
            Bundel bundel = (Bundel) nv.getUserData();
            if(bundel != null){
                addPagina.setDisable(false);
                addCategorie.setDisable(false);
                env.setSelectedBundel(bundel);
            } else {
                addPagina.setDisable(true);
                addCategorie.setDisable(true);
            }
        });

        //alle open tabs, van laatste keer dat de applicatie open was, openen
        EnvironmentObject environmentObject = env.getEnvironmentObject();
        try{
            ArrayList<Bundel> openBundels = environmentObject.getOpenBundels();
            for(Bundel openBundel : openBundels){
                Tab nieuweTab = createNewTab(openBundel, env);
                rootTabPane.getTabs().add(nieuweTab);
                if(openBundel == env.getSelectedBundel()){
                    rootTabPane.getSelectionModel().select(nieuweTab);
                }
            }
        } catch (Exception e){
            Logger.log("MainWindowController.initialize", "Kon de open bundels niet laden. Open Bundels wordt gereset");
            ObjectGenerator gen = env.getObjectGenerator();
            EnvironmentObject emptyEnvironmentObject = gen.getEnvironmentObject();
            env.clearEnvironmentObjectFile();
            env.setEnvironmentObject(emptyEnvironmentObject);
            //gen.
        }
    }

    /*
    Event Handlers
     */

    //event handlers File Menu
    @FXML
    private void newBundle(){
        BundelDirectoyChooser mapChooser = new BundelDirectoyChooser("Selecteer een lege map");
        File dir = mapChooser.getMap();
        if (dir != null) {
            String directoryPath = dir.getPath();
            BundelLogic bundelLogic = gen.getBundelLogic(directoryPath);
            bundelLogic.createBundel();

            //aangemaakte bundel laden
            Bundel nieuweBundel = loadBundel(directoryPath);
            Tab nieuweTab = createNewTab(nieuweBundel, BundelBEnvironment.getInstance());
            rootTabPane.getTabs().add(nieuweTab);
            rootTabPane.getSelectionModel().select(nieuweTab);;
        } else {
            Logger.log("RootWindowUI.newBundle","Dir heeft waarde null");
        }
    }

    @FXML
    private void openBundle(){
        BundelBEnvironment env = BundelBEnvironment.getInstance();

        //locatie van de bundel opvragen
        BundelDirectoyChooser mapChooser = new BundelDirectoyChooser("Selecteer een map met een bundel");
        File dir = mapChooser.getMap();
        if (dir != null) {
            //BundelObject aanmaken
            String bundlePath = dir.getPath();
            BundelLogic bundelLoader = gen.getBundelLogic(bundlePath);
            if(!bundelLoader.doesBundelExist()){
                Logger.log(this.getClass().toString()+".openBundle", "BundelBBundel bestaat niet");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Er is geen BundelBBundel in de geselecteerde map gevonden");
                alert.setTitle("BundelBBundel niet gevonden");
                alert.setHeaderText("Onjuiste map geselecteerd");
                alert.showAndWait();
            } else if(env.bundelIsOpen(bundlePath)){
                Logger.log(this.getClass().toString()+".openBundle", "BundelBBundel is al open");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Bundels kunnen niet dubbel geopend worden");
                alert.setTitle("De bundel is al geopend");
                alert.setHeaderText("De geselecteerde bundel is al open");
                alert.showAndWait();
            } else {
                Bundel bundel = loadBundel(bundlePath);

                Tab nieuweTab = createNewTab(bundel, env);

                //tab toevoegen aan de rootTabPane en selecteren/weergeven
                rootTabPane.getTabs().add(nieuweTab);
                rootTabPane.getSelectionModel().select(nieuweTab);
            }
        }
    }

    @FXML
    private void addNewCategorie(){
        popUpBundelEdit("/nl/bastiaansierd/bundleb/ui/views/CategorieAdd.fxml", "Voeg een Categorie toe");
    }

    @FXML
    private void addNewPage(){
        popUpBundelEdit("/nl/bastiaansierd/bundleb/ui/views/PageAdd.fxml", "Voeg een Pagina toe");
    }

    @FXML
    private void closeBundleB(){
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    //event handlers Settings menu
    public void setRootDirSetting(){
        BundelDirectoyChooser mapChooser = new BundelDirectoyChooser("Selecteer een de map om je Bundels in op te slaan");
        File dir = mapChooser.getMap();
        if (dir != null) {
            String directoryPath = dir.getPath();
            SettingTracker settingTracker = gen.getSettingTracker();
            settingTracker.setBundelRootDirectory(directoryPath);
        } else {
            Logger.log("RootWindowUI.newBundle","Dir heeft waarde null");
        }
    }

    //event handlers Help menu
    public void viewWelkom(){
        Tab tab = new Tab("Welkom");
        tab.setUserData(null);

        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.loadContent(welkomTekst);

        tab.setContent(webView);
        rootTabPane.getTabs().add(tab);
    }

    /*
    Einde Event Handlers
     */


    /*
    Ondersteunende Methodes
     */

    //bundel laden
    private Bundel loadBundel(String bundlePath){
        BundelBEnvironment env = BundelBEnvironment.getInstance();
        EnvironmentObject envObject = env.getEnvironmentObject();

        //verplaatsen naar logicalaag
        BundelLogic bundelLoader = gen.getBundelLogic(bundlePath);
        Bundel bundel = bundelLoader.getBundel();

        //selected bundel toevoegen aan BundelBEnvironment
        envObject.addOpenBundel(bundel);
        env.setEnvironmentObject(envObject);
        env.setSelectedBundel(bundel);
        env.saveEnvironment();

        return bundel;
    }

    private void loadTabContent(Tab tab, Bundel bundel){
        //controller voor een nieuwe tab aanmaken, bundel meegeven
        BundelTabController tabController = new BundelTabController(bundel);

        //nieuwe FXMLLoader instance aanmaken, fxml bestand en controller meegeven
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/nl/bastiaansierd/bundleb/ui/views/BundelTab.fxml"));
        loader.setController(tabController);
        try {
            SplitPane splitPane = loader.load();
            tab.setContent(splitPane);
        } catch (IOException e) {
            Logger.log("MainWindowController.openBundle", e.toString());
        }
    }

    //bundel tab aanmaken
    private Tab createNewTab(Bundel bundel, BundelBEnvironment env){

        //nieuwe tab aanmaken
        Tab tab = new Tab(bundel.getNaam());
        tab.setUserData(bundel);

        loadTabContent(tab, bundel);

        EnvironmentObject environmentObject = env.getEnvironmentObject();

        tab.setOnClosed(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                environmentObject.removeOpenBundel(bundel);
                env.setEnvironmentObject(environmentObject);
                Tab newSelectedTab = rootTabPane.getSelectionModel().getSelectedItem();
                env.setSelectedBundel((Bundel) newSelectedTab.getUserData());
                env.saveEnvironment();
            }
        });

        return tab;
    }

    //tab herladen
    private void reloadSelectedBundel(){
        BundelBEnvironment env = BundelBEnvironment.getInstance();

        Tab selectedBundelTab = rootTabPane.getSelectionModel().getSelectedItem();
        Bundel selectedBundel = env.getSelectedBundel();

        env.getEnvironmentObject().removeOpenBundel(selectedBundel);

        Bundel reloadedBundel = loadBundel(selectedBundel.getBundelPath());

        selectedBundelTab.setUserData(reloadedBundel);

        loadTabContent(selectedBundelTab, reloadedBundel);
    }

    private void popUpBundelEdit(String fxmlViewPath, String title){
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlViewPath));
        HBox hbox = null;
        try {
            hbox = loader.load();
        } catch (IOException e) {
            Logger.log("MainWindowController.popUp", e.getMessage());
        }

        Scene scene = new Scene(hbox);
        Stage popUpStage = new Stage();
        popUpStage.setTitle(title);
        popUpStage.setScene(scene);
        popUpStage.setOnHidden(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                // Refresh the parent window here
                reloadSelectedBundel();
            }
        });
        popUpStage.initModality(Modality.WINDOW_MODAL);
        popUpStage.initOwner(rootPane.getScene().getWindow());
        popUpStage.show();
    }

    /*
    Einde ondersteunende methodes
     */

    /*
    listeners
    Deze functie reageert op het veranderen van de geselecteerde tab
    Hij wordt aangeroepen in logic/BundelBEnvironment en bovenaan in de
    constructor toegevoegd aan de listeners van BundelBEnvironment
     */
    @Override
    public void selectedBundelChanged(){
        BundelBEnvironment env = BundelBEnvironment.getInstance();
        EnvironmentObject envObject = env.getEnvironmentObject();
        if (envObject.getSelectedBundel() != null){
            addPagina.setDisable(false);
            addCategorie.setDisable(false);
        } else {
            addPagina.setDisable(true);
            addCategorie.setDisable(true);
        }
    }
}
