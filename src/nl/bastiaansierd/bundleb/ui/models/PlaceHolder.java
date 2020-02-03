package nl.bastiaansierd.bundleb.ui.models;

import nl.bastiaansierd.bundleb.enums.LeafType;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.BundelLeaf;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.Category;

import java.io.Serializable;

public class PlaceHolder implements BundelLeaf, Serializable {
    private Category category;
    private int index;

    PlaceHolder(Category cat, int id){
        this.category = cat;
        this.index = id;
    }

    @Override
    public LeafType getLeafType() {
        return LeafType.PLACEHOLDER;
    }

    @Override
    public String getName() {
        return null;
    }

    public Category getCategory() {
        return category;
    }

    public int getIndex() {
        return index;
    }
}
