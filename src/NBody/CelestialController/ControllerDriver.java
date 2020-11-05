package NBody.CelestialController;
/*
    This class is just driver code that I used to test out the controller classes.  If you want to run the main application,
    then go to NBody.main.NBodyRunner
*/
import java.io.IOException;

public class ControllerDriver {
    public static void main(String[] args) throws IOException, Exception {
        CelestialDataParser cdp = new CelestialDataParser("test.txt");
        System.out.println(cdp.getCelestialMetaData().toString());
        System.out.println(cdp.getCelestialRecords().toString());
    }
}
