package nl.bastiaansierd.bundleb.logic;

import nl.bastiaansierd.bundleb.interfaces.logic.BundelLogic;
import nl.bastiaansierd.bundleb.interfaces.logic.IntroLogic;
import nl.bastiaansierd.bundleb.interfaces.logic.ObjectGenerator;
import nl.bastiaansierd.bundleb.interfaces.logic.SettingTracker;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.Bundel;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.Category;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.EnvironmentObject;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.Header;
import nl.bastiaansierd.bundleb.logic.objects.BundelBBundel;
import nl.bastiaansierd.bundleb.logic.objects.BundelBCategory;
import nl.bastiaansierd.bundleb.logic.objects.BundelBEnvironmentObject;
import nl.bastiaansierd.bundleb.logic.objects.BundelBHeader;

public class BundelBObjectGenerator implements ObjectGenerator {
    public IntroLogic getIntroLogic(){
        return new BundelBIntroLogic();
    }

    public Bundel getBundel(String bundelPath, String bundelName, Category tree){
        return new BundelBBundel(bundelPath, bundelName, tree);
    }

    public Category getCategorie(String naam){
        return new BundelBCategory(naam);
    }

    public Header getHeader(String name, String address){
        return new BundelBHeader(name, address);
    }

    public BundelLogic getBundelLogic(String bundelPath){
        return new BundelBBundelLogic(bundelPath);
    }

    public SettingTracker getSettingTracker(){
        return new BundelBSettingTracker();
    }

    public EnvironmentObject getEnvironmentObject(){ return new BundelBEnvironmentObject(); }
}
