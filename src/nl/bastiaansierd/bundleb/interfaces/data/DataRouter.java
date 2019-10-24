package nl.bastiaansierd.bundleb.interfaces.data;

import org.w3c.dom.Document;

import java.io.Serializable;

public interface DataRouter {
    /*
    Router
     */
    String getIntroTekst();

    BundelData getBundelData();

    <ObjectType extends Serializable> void saveSettingsObject(ObjectType object);

    <ObjectType extends Serializable> ObjectType loadSettingsObject(ObjectType newObject);

    <ObjectType extends Serializable> void saveEnvironmentObject(ObjectType object);

    <ObjectType extends Serializable> ObjectType loadEnvironmentObject() throws Exception;

    <ObjectType extends Serializable> void clearEnvironment();
}
