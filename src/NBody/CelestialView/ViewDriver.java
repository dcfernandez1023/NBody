package NBody.CelestialView;
/*
    This class is just driver code that I used to test out the UI classes.  If you want to run the main application,
    then go to NBody.main.NBodyRunner
*/
import NBody.CelestialController.CelestialDataParser;

public class ViewDriver {
    public static void main(String[] args) throws Exception {
        CelestialDataParser cdp = new CelestialDataParser("test.txt");
        CelestialRenderer renderer = new CelestialRenderer("test.txt");
        renderer.render();
    }
}
