package nl.bastiaansierd.bundleb.data.dataAccessConnectors;

import java.io.File;

public class BundelFoundCheck {
    private boolean bundelPresent = false;

    public BundelFoundCheck(String directory) {
        File tree = new File(directory + "\\tree.xml");
        if(tree.exists()){
            bundelPresent = true;
        }
    }

    public boolean isBundelPresent() {
        return bundelPresent;
    }
}
