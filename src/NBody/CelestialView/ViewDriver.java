package NBody.CelestialView;

import NBody.CelestialController.CelestialDataParser;

public class ViewDriver
{
    public static void main(String[] args) throws Exception
    {
        CelestialDataParser cdp = new CelestialDataParser("test");
        CelestialRenderer renderer = new CelestialRenderer(cdp.getCelestialRecords(), cdp.getCelestialMetaData(), "test");
        renderer.render();
    }
}
