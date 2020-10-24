package NBody.CelestialController;

import java.io.IOException;

public class ControllerDriver {
    public static void main(String[] args) throws IOException, Exception {
        CelestialDataParser cdp = new CelestialDataParser("test");
        System.out.println(cdp.getCelestialMetaData().toString());
        System.out.println(cdp.getCelestialRecords().toString());
    }
}
