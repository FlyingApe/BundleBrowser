package nl.bastiaansierd.bundleb.logic;

import nl.bastiaansierd.bundleb.data.BundelBDataRouter;
import nl.bastiaansierd.bundleb.interfaces.data.DataRouter;
import nl.bastiaansierd.bundleb.interfaces.logic.ObjectGenerator;
import nl.bastiaansierd.bundleb.interfaces.logic.SelectedBundelListener;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.Bundel;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.BundelLeaf;
import nl.bastiaansierd.bundleb.interfaces.logic.objects.EnvironmentObject;
import nl.bastiaansierd.datalogger.logic.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
Deze class houd de state van de applicatie bij. Dit zorgt ervoor dat de bundels geladen kunnen worden die
openstonden bij de laatste keer dat BundelB gesloten is.

Ook slaat de applicatie hier op welke bundel op dit moment open staat. Zo kunnen verschillende onderdelen
reageren op gebruikersinput en de input naar de juiste bundel sturen.

Tenslotte dient de classe als plek waar de DataRouter en ObjectGenerator aangemaakt worden,
deze classen zijn elk verantwoordelijk voor het aanmaken en ophalen van nieuwe objecten binnen hun eigen laag

Mogelijk kan functionaliteit van deze class gesplitst worden (Single-Responsibility)
Het is echter prettig om 1 object te hebben waar alle benodigde gegevens vandaan gehaald kunnen worden,
deze klasse knoopt dus de hele applicatie aan elkaar.
*/
public class BundelBEnvironment {
    private DataRouter dataRouter = new BundelBDataRouter();
    private ObjectGenerator objectGenerator = new BundelBObjectGenerator();
    private EnvironmentObject environmentObject = null;
    private List<SelectedBundelListener> listeners = new ArrayList<>();
    private Lock environmentLock;

    //Singelton instance
    private static BundelBEnvironment instance = null;

    private BundelBEnvironment(){
        /*
        Deze lock zorgt ervoor dat er niet tegelijkertijd geschreven en gelezen wordt, of dat 2 verschillende classen
        tegelijkertijd proberen te schrijven/lezen
         */
        environmentLock = new ReentrantLock();
    }

    public static BundelBEnvironment getInstance(){
        /* singelton initialisatie*/
        if(instance == null){
            instance = new BundelBEnvironment();
        }
        return instance;
    }

    public void initialize() throws Exception{
        /*
        deze functie laad omgevingsvariabelen vanuit een de datalaag in deze singelton,
        op deze manier zijn deze omgevingsvariabelen overal in de applicatie beschikbaar.
         */
        environmentLock.lock();
        try {
            this.environmentObject = dataRouter.<EnvironmentObject>loadEnvironmentObject();
        }
        finally {
            environmentLock.unlock();
        }
    }

    public void saveEnvironment(){
        environmentLock.lock();
        try {
            if (environmentObject != null) {
                dataRouter.<EnvironmentObject>saveEnvironmentObject(environmentObject);
            } else {
                Logger.log("BundelBEnvironment.saveEnvironment", "environmentObject has value null");
            }
        }
        finally {
            environmentLock.unlock();
        }
    }

    public void setEnvironmentObject(EnvironmentObject environmentObject) {
        this.environmentObject = environmentObject;
        saveEnvironment();
    }

    public void clearEnvironmentObjectFile(){
        dataRouter.clearEnvironment();
    }

    public EnvironmentObject getEnvironmentObject() {
        return environmentObject;
    }

    public void close(){
        saveEnvironment();
        dataRouter = null;
        environmentObject = null;
        instance = null;
        Logger.log("BundelBEnvironment.close", "BundleB omgeving succesvol afgesloten");
    }

    //bijhouden welke bundel op dit moment geopend is, zodat de applicatie weet in welke bundel gewerkt kan worden
    public Bundel getSelectedBundel() {
        return environmentObject.getSelectedBundel();
    }

    public void setSelectedBundel(Bundel selectedBundel) {
        environmentObject.setSelectedBundel(selectedBundel);
        for (SelectedBundelListener observer : listeners)
            observer.selectedBundelChanged();
    }

    //checken of een bundel al geladen is; controleren op filePath
    public boolean bundelIsOpen(String filePath){
        for(Bundel bundel : environmentObject.getOpenBundels()){
            if(bundel.getBundelPath().equals(filePath)){
                return true;
            }
        }
        return false;
    }

    /*
    De volgende twee getters geven de twee objecten van waaruit nieuwe objecten aangemaakt worden
    Hierdoor zijn alle new instances gegroepeerd in een apparte classe voor zowel de data- als logicalaag
     */

    //DataRouter getter
    //vanuit deze dataRouter word alle data geladen
    public DataRouter getDataRouter(){
        return dataRouter;
    }

    //Object Generator
    //vanuit deze generator worden nieuwe objecten geladen
    public ObjectGenerator getObjectGenerator(){return objectGenerator;}


    /*
    listeners reageren op een event. In dit geval op het veranderen van de geselecteerde/open BundelTab
     */

    //listener classes zijn in staat te reageren op het veranderen van de geselecteerde bundel
    public void addListener(SelectedBundelListener toAdd){
        listeners.add(toAdd);
    }

    public Boolean listenerListed(SelectedBundelListener listed){
        if (listeners.contains(listed)){
            return true;
        } else {
            return false;
        }
    }
}