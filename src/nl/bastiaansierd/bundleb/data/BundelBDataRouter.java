package nl.bastiaansierd.bundleb.data;

import nl.bastiaansierd.bundleb.data.dataAccessConnectors.*;
import nl.bastiaansierd.bundleb.interfaces.data.BundelData;
import nl.bastiaansierd.bundleb.interfaces.data.DataRouter;
import nl.bastiaansierd.datalogger.logic.Logger;

import java.io.Serializable;

public class BundelBDataRouter implements DataRouter {
    /*
    Router
     */
    public String getIntroTekst(){
        MarkdownFileLoader loader = new MarkdownFileLoader();
        loader.loadFile("out/production/BundleBrowser/nl/bastiaansierd/bundleb/ui/views/welkom.md");
        String tekst = loader.getAsString();
        return tekst;
    }

    public BundelData getBundelData(){
        return new BundelBBundelData();
    }

    public <ObjectType extends Serializable> void saveSettingsObject(ObjectType object){
        ObjectFileConnector<ObjectType> connector = new ObjectFileConnector<>("src/nl/bastiaansierd/bundleb/", "settings.dat");
        connector.saveObject(object);
    }

    public <ObjectType extends Serializable> ObjectType loadSettingsObject(ObjectType newObject){
        ObjectType loadableObject = null;
        ObjectFileConnector<ObjectType> connector = new ObjectFileConnector<>("src/nl/bastiaansierd/bundleb/", "settings.dat");
        try {
            loadableObject = connector.loadObject();
        } catch (Exception e) {
            Logger.log("BundelBSettingTracker.constructor", e.getMessage());
            connector.saveObject(newObject);
            try {
                loadableObject = connector.loadObject();
            } catch (Exception ex) {
                Logger.log("BundelBSettingTracker.constructor", "Het is niet gelukt om een setting.dat file aan te maken");
            }
        }

        return loadableObject;
    }

    public <ObjectType extends Serializable> void saveEnvironmentObject(ObjectType object){
        ObjectFileConnector<ObjectType> connector = new ObjectFileConnector<>("src/nl/bastiaansierd/bundleb/", "bundleBEnv.dat");
        connector.saveObject(object);
    }

    public <ObjectType extends Serializable> ObjectType loadEnvironmentObject() throws Exception{
        ObjectFileConnector<ObjectType> connector = new ObjectFileConnector<>("src/nl/bastiaansierd/bundleb/", "bundleBEnv.dat");
        ObjectType envData = null;
        envData = connector.loadObject();

        return envData;
    }

    public <ObjectType extends Serializable> void clearEnvironment(){
        ObjectFileConnector<ObjectType> connector = new ObjectFileConnector<>("src/nl/bastiaansierd/bundleb/", "bundleBEnv.dat");
        connector.clear();
    }
}
