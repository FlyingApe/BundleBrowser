package nl.bastiaansierd.bundleb.logic.objects;

import nl.bastiaansierd.bundleb.interfaces.logic.objects.Bundel;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.Categorie;

import java.io.Serializable;

public class BundelBBundel implements Bundel, Serializable {
    private String naam;
    private String bundelPath;
    private Categorie tree;

    //constructor
    public BundelBBundel(String naam, String bundelPath, Categorie tree){
        this.naam = naam;
        this.bundelPath = bundelPath;
        this.tree = tree;
    }

    //getters
    public String getNaam() {
        return naam;
    }

    public String getBundelPath() {
        return bundelPath;
    }

    public Categorie getTree() {
        return tree;
    }
}

