package nl.bastiaansierd.bundleb.logic;

import nl.bastiaansierd.bundleb.interfaces.data.DataRouter;
import nl.bastiaansierd.bundleb.interfaces.logic.SettingTracker;
import nl.bastiaansierd.bundleb.logic.objects.BundelBSettingsObject;

/*
Deze class laad en save't settings. Op dit moment alleen wat de root directory voor opgeslagen bundels is.
*/

public class BundelBSettingTracker implements SettingTracker {
    private DataRouter dataRouter = BundelBEnvironment.getInstance().getDataRouter();
    private BundelBSettingsObject settings;

    public BundelBSettingTracker() {
        BundelBSettingsObject leegSettingsObject = new BundelBSettingsObject();
        settings = dataRouter.loadSettingsObject(leegSettingsObject);
    }

    public String getBundelRootDirectory() {
        return settings.getBundelRootDirectory();
    }

    public void setBundelRootDirectory(String bundelRootDirectory) {
        settings.setBundelRootDirectory(bundelRootDirectory);
        dataRouter.saveSettingsObject(settings);
    }
}
