package nl.bastiaansierd.bundleb.logic.objects;

import nl.bastiaansierd.bundleb.enums.LeafType;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.BundelLeaf;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.Categorie;

import java.io.Serializable;
import java.util.ArrayList;

public class BundelBCategorie implements Categorie, Serializable {
    private final LeafType leafType = LeafType.CATEGORY;
    private String naam;
    private ArrayList<BundelLeaf> children = new ArrayList<>();

    public BundelBCategorie(String naam) {
        this.naam = naam;
    }

    //setters
    public void addChild(BundelLeaf leaf){
        children.add(leaf);
    }

    //getters
    public LeafType getLeafType() {
        return leafType;
    }

    public String getName() {
        return naam;
    }

    public ArrayList<BundelLeaf> getChildren() {
        return children;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(getLeafType().toString() + ": " + getName() + "\n");
        for (BundelLeaf leaf: getChildren()){
            sb.append(leaf.toString().replaceAll("(?m)^", "\t"));
        }

        return sb.toString();
    }
}
