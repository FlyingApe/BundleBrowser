package nl.bastiaansierd.bundleb.ui.models;

import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import nl.bastiaansierd.bundleb.interfaces.logic.SettingTracker;
import nl.bastiaansierd.bundleb.logic.BundelBEnvironment;
import nl.bastiaansierd.datalogger.logic.Logger;

import java.io.File;

public class BundelDirectoyChooser{
    private String kopTekst;

    public BundelDirectoyChooser(String kopTekst){
        this.kopTekst = kopTekst;
    }

    public File getMap(){
        Stage stage = new Stage();
        DirectoryChooser dirChooser = new DirectoryChooser();
        dirChooser.setTitle(kopTekst);

        File directory = new File(System.getProperty("user.home"));

        SettingTracker settingTracker = BundelBEnvironment.getInstance().getObjectGenerator().getSettingTracker();
        try{
            directory = new File(settingTracker.getBundelRootDirectory());
        } catch (Exception e){
            Logger.log("BundelBDirectoryChooser.getMap", "Standaard directory niet aangegeven ");
        }
        dirChooser.setInitialDirectory(directory);
        File dir = dirChooser.showDialog(stage);
        return dir;
    }
}
