package nl.bastiaansierd.bundleb.logic.objects;

import nl.bastiaansierd.bundleb.interfaces.logic.objects.Bundel;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.EnvironmentObject;

import java.io.Serializable;
import java.util.ArrayList;

public class BundelBEnvironmentObject implements EnvironmentObject, Serializable {
    private Bundel selectedBundel = null;
    private ArrayList<Bundel> openBundels = new ArrayList<>();

    //getters
    public ArrayList<Bundel> getOpenBundels() {
        return openBundels;
    }

    public Bundel getSelectedBundel(){
        return selectedBundel;
    }

    //setters
    public void setSelectedBundel(Bundel selectedBundel) {
        this.selectedBundel = selectedBundel;
        //for (SelectedBundelListener observer : listeners)
        //    observer.selectedBundelChanged();
    }

    public void addOpenBundel(Bundel bundel){
        openBundels.add(bundel);
    }

    public void removeOpenBundel(Bundel bundel){
        openBundels.remove(bundel);
    }

}
