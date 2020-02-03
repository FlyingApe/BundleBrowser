package nl.bastiaansierd.bundleb.ui.models;

import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import nl.bastiaansierd.bundleb.interfaces.logic.SettingTracker;
import nl.bastiaansierd.bundleb.logic.BundelBEnvironment;
import nl.bastiaansierd.datalogger.logic.Logger;

import java.io.File;
import java.nio.file.Files;

public class BundelDirectoyChooser{
    private String kopTekst;

    public BundelDirectoyChooser(String kopTekst){
        this.kopTekst = kopTekst;
    }

    public File getMap(){
        Stage stage = new Stage();
        DirectoryChooser dirChooser = new DirectoryChooser();
        dirChooser.setTitle(kopTekst);

        SettingTracker settingTracker = BundelBEnvironment.getInstance().getObjectGenerator().getSettingTracker();
        try{
            File directory = new File(settingTracker.getBundelRootDirectory());
            if (!Files.exists(directory.toPath())){
                settingTracker.setBundelRootDirectory(System.getProperty("user.home"));
            }
        } catch (Exception e){
            Logger.log("BundelBDirectoryChooser.getMap", "Standaard directory niet aangegeven ");
            settingTracker.setBundelRootDirectory(System.getProperty("user.home"));
        }


        dirChooser.setInitialDirectory(new File (settingTracker.getBundelRootDirectory()));
        File dir = dirChooser.showDialog(stage);
        return dir;
    }
}
