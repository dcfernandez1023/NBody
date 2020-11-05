package NBody.main;
/*
    This class is the 'main' for this entire application. By running the main method below, all components of the application work together to
    start and run the application.
*/
import NBody.CelestialView.CelestialRenderer;

public class NBodyRunner {
    public static void main(String[] args) {
        //get command line arguments
        String fileName;
        try {
            fileName = args[0];
        }
        catch(ArrayIndexOutOfBoundsException aiob) {
            fileName = "";
        }
        //render the Celestials
        CelestialRenderer renderer = new CelestialRenderer(fileName);
        renderer.render();
    }
}
