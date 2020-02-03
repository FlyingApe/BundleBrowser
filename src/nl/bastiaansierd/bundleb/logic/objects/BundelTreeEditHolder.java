package nl.bastiaansierd.bundleb.logic.objects;

import nl.bastiaansierd.bundleb.interfaces.logic.objects.BundelLeaf;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.Category;
import nl.bastiaansierd.bundleb.ui.models.PlaceHolder;

public class BundelTreeEditHolder {
    private static BundelTreeEditHolder instance = null;
    private BundelLeaf movingLeaf;
    private Category parent;
    private PlaceHolder placeHolder;

    public static void init(){
        instance = new BundelTreeEditHolder();
    }

    public static BundelTreeEditHolder getInstance(){
        if(instance == null){
            System.out.println("instance is null, initialize BundelTreeEditHolder with its init() method");
        }
        return instance;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }
    public void setMovingLeaf(BundelLeaf movingLeaf) {
        this.movingLeaf = movingLeaf;
    }

    public void setPlaceHolder(PlaceHolder placeHolder) {
        this.placeHolder = placeHolder;
    }

    public void moveLeaf(){
        parent.removeChild(movingLeaf);
        placeHolder.getCategory().insertChild(movingLeaf, placeHolder.getIndex());
        movingLeaf = null;
        placeHolder = null;
    }
}
