package nl.bastiaansierd.bundleb.logic.objects;

import java.io.Serializable;

public class BundelBSettingsObject implements Serializable {
    private String bundelRootDirectory;

    public String getBundelRootDirectory() {
        return bundelRootDirectory;
    }

    public void setBundelRootDirectory(String bundelRootDirectory) {
        this.bundelRootDirectory = bundelRootDirectory;
    }
}
