package nl.bastiaansierd.bundleb.interfaces.logic.objects;

import java.util.ArrayList;

public interface Categorie extends BundelLeaf{
    void addChild(BundelLeaf leaf);
    ArrayList<BundelLeaf> getChildren();
}
