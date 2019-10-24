package nl.bastiaansierd.bundleb.logic.objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BundelBSettingsObjectTest {
    BundelBSettingsObject settingsObject = new BundelBSettingsObject();

    @Test
    void getBundelRootDirectory() {
        settingsObject.setBundelRootDirectory("C:\\fakepath");
        assertEquals("C:\\fakepath", settingsObject.getBundelRootDirectory());
    }

    @Test
    void setBundelRootDirectory() {
        settingsObject.setBundelRootDirectory("C:\\fakepath");
        assertEquals("C:\\fakepath", settingsObject.getBundelRootDirectory());
    }
}