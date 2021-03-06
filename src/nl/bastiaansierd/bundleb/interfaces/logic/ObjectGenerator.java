package nl.bastiaansierd.bundleb.interfaces.logic;

import nl.bastiaansierd.bundleb.interfaces.logic.objects.Bundel;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.Category;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.EnvironmentObject;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.Header;

public interface ObjectGenerator {
    IntroLogic getIntroLogic();

    Bundel getBundel(String bundelPath, String bundelName, Category tree);

    Category getCategorie(String naam);

    Header getHeader(String naam, String lokatie);

    BundelLogic getBundelLogic(String bundelPath);

    SettingTracker getSettingTracker();

    EnvironmentObject getEnvironmentObject();
}
