package NBody.main;

import NBody.CelestialController.CelestialDataParser;
import NBody.CelestialView.CelestialRenderer;

public class NBodyRunner {
    public static void main(String[] args) throws Exception {
        CelestialDataParser cdp = new CelestialDataParser("test");
        CelestialRenderer renderer = new CelestialRenderer(cdp.getCelestialRecords(), cdp.getCelestialMetaData(), "test");
        renderer.render();
    }
}
