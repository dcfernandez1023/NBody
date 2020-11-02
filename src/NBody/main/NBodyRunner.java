package NBody.main;

import NBody.CelestialView.CelestialRenderer;

public class NBodyRunner {
    public static void main(String[] args) {
        CelestialRenderer renderer = new CelestialRenderer("test");
        renderer.render();
    }
}
