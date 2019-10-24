package nl.bastiaansierd.bundleb.logic.objects;

import nl.bastiaansierd.bundleb.interfaces.logic.objects.Bundel;
import nl.bastiaansierd.bundleb.logic.BundelBEnvironment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BundelBEnvironmentObjectTest {
    BundelBEnvironmentObject envObject = new BundelBEnvironmentObject();
    Bundel nieuweBundel = new BundelBBundel("TestBundel", "c:\\fakepath", new BundelBCategorie("EmptyCat"));

    @Test
    void getOpenBundels() {
        assertEquals(0, envObject.getOpenBundels().size());
    }

    @Test
    void getSelectedBundel() {
        assertNull(envObject.getSelectedBundel());
    }

    @Test
    void setSelectedBundel() {
        envObject.addOpenBundel(nieuweBundel);
        envObject.setSelectedBundel(nieuweBundel);
        assertNotNull(envObject.getSelectedBundel());
        assertSame(nieuweBundel, envObject.getSelectedBundel());
    }

    @Test
    void addOpenBundel() {
        assertEquals(0, envObject.getOpenBundels().size());
        envObject.addOpenBundel(nieuweBundel);
        assertEquals(1, envObject.getOpenBundels().size());
    }

    @Test
    void removeOpenBundel() {
        envObject.addOpenBundel(nieuweBundel);
        assertEquals(1, envObject.getOpenBundels().size());
        envObject.removeOpenBundel(nieuweBundel);
        assertEquals(0, envObject.getOpenBundels().size());
    }
}