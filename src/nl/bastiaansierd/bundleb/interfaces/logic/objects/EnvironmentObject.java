package nl.bastiaansierd.bundleb.interfaces.logic.objects;

import java.io.Serializable;
import java.util.ArrayList;

public interface EnvironmentObject extends Serializable {
    //getters
    ArrayList<Bundel> getOpenBundels();

    Bundel getSelectedBundel();


    //setters
    void setSelectedBundel(Bundel selectedBundel);

    void addOpenBundel(Bundel bundel);

    void removeOpenBundel(Bundel bundel);
}
