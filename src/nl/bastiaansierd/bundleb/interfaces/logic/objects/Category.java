package nl.bastiaansierd.bundleb.interfaces.logic.objects;

import java.util.ArrayList;

public interface Category extends BundelLeaf{
    void addChild(BundelLeaf leaf);
    void insertChild(BundelLeaf leaf, int index);
    void removeChild(BundelLeaf leaf);
    ArrayList<BundelLeaf> getChildren();
}
