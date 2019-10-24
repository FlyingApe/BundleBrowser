package nl.bastiaansierd.bundleb.data.dataAccessConnectors;

import nl.bastiaansierd.datalogger.logic.Logger;

import java.io.*;
import java.lang.module.ModuleDescriptor;

public class ObjectFileConnector<ObjectType extends java.io.Serializable> {
    private String filePath;
    private String fileName;
    private ObjectType saveableObject;

    public ObjectFileConnector(String filePath, String fileName) {
        this.filePath = filePath;
        this.fileName = fileName;
    }

    public void setObject(ObjectType object){
        this.saveableObject = object;
    }

    public Boolean saveObject(){
        Boolean succes = false;
        ObjectOutputStream out = null;
        File file = new File(filePath + fileName);
        try {
            out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject((ObjectType) saveableObject);
        }
        catch (FileNotFoundException fnfe) {
            Logger.log("ObjectFileConnector.saveObject", fnfe.toString());
        } catch (IOException ioe) {
            Logger.log("ObjectFileConnector.saveObject", ioe.toString());
        } finally {
            try {
                out.close();
                succes = true;
            } catch (IOException ioe) {
                Logger.log("ObjectFileConnector.saveObject", ioe.toString());
            } catch (NullPointerException npe){
                Logger.log("ObjectFileConnector.saveObject", npe.toString());
            }
        }
        return succes;
    }

    public Boolean saveObject(ObjectType object){
        Boolean succes;
        setObject(object);
        succes = saveObject();
        return succes;
    }

    public ObjectType loadObject() throws Exception{
        ObjectType loadable = null;
        ObjectInputStream in = null;

        File file = new File(filePath + fileName);
        if(file.exists()){
            try {
                in = new ObjectInputStream(new FileInputStream(file));
                loadable = (ObjectType) in.readObject();
            }
            catch (FileNotFoundException fnfe) {
                Logger.log("ObjectFileConnector.loadObject", fnfe.toString());
            } catch (IOException ioe) {
                Logger.log("ObjectFileConnector.loadObject", ioe.toString());
            } catch (ClassNotFoundException cnfe) {
                Logger.log("ObjectFileConnector.loadObject", cnfe.toString());
            } finally {
                try {
                    in.close();
                } catch (IOException ioe) {
                    Logger.log("ObjectFileConnector.loadObject", ioe.toString());
                }
            }
        }
        else {
            throw new Exception("bestand bestaad niet -- " + filePath + fileName);
        }

        return loadable;
    }

    public void clear() {
        ObjectOutputStream out = null;
        File file = new File(filePath + fileName);
        try {
            out = new ObjectOutputStream(new FileOutputStream(file));
            out.flush();
            out.close();
        } catch (Exception e) {
            Logger.log("ObjectFileConnector.clear", "Kon het bestand legen");
        }
    }
}

