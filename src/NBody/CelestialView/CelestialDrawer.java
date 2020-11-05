package NBody.CelestialView;
/*
    This class extends JPanel and implements ActionListener, thus allowing instantiations of this class to be rendered on a JFrame.
    The purpose of this class is to maintain a Container of Celestial objects, draw each Celestial object onto the JFrame, and call methods
    to update the positions and velocities of each Celestial object.
*/

import NBody.CelestialController.CelestialDataParser;
import NBody.CelestialModel.Celestial;
import NBody.CelestialModel.Container;
import NBody.CelestialModel.ContainerArrayList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class CelestialDrawer extends JPanel implements ActionListener {
    private Container<Celestial> celestials; //Container of Celestial objects
    private Timer timer = new Timer(5, this); //timer to fire the action listener on this class (since this class extends JPanel)
    private Container<Color> colors;
    /*
        * PARAMS:
            * int xPixels - size of this class on the x axis (in pixels)
            * int yPixels - size of this class on the y axis (in pixels)
            * String fileName - name of the file to be read
        * DESCRIPTION: constructs new CelestialDrawer, which in turn constructs a CelestialDataParser to read the CSV file and populate
                       the Container of Celestial objects; also sets the size of this class to the desired pixels that the user defines
                       and gets random colors for each Celestial object
        * RETURN: none
    */
    public CelestialDrawer(int xPixels, int yPixels, String fileName) throws Exception {
        CelestialDataParser cdp = new CelestialDataParser(fileName); //read CSV file
        this.celestials = cdp.getCelestialRecords(); //populate Container of Celestial objects
        this.setSize(xPixels, yPixels); //set size of window to user defined size(default will be 768x768)
        this.colors = new ContainerArrayList<>(this.celestials.size());
        Random rand = new Random();
        for(int i = 0; i < this.celestials.size(); i++) {
            //generate random color
            float red = rand.nextFloat();
            float green = rand.nextFloat();
            float blue = rand.nextFloat();
            Color randColor = new Color(red, green, blue);
            this.colors.add(randColor);
        }
    }
    /*
        * PARAMS: Graphics g - the Graphics object that gets passed to this overriden method (under the hood) to render shapes
        * DESCRIPTION: loops through and draws Celestials based on their coordinates and size
        * RETURN: none
    */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int i = 0; i < this.celestials.size(); i++) {
            Celestial c = this.celestials.get(i);
            double x = c.getX();
            double y = c.getY();
            double size = c.getSize();
            g.fillOval((int) x, (int) y, (int) size, (int) size);
            g.setColor(this.colors.get(i));
        }
        this.timer.start();
    }
    //actionPerformed overridden method that fires every interval specified in the Timer object data member of this class
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            this.updateCelestials(); //update all positions and velocities of each Celestial object relative to one another
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    /*
        * PARAMS: none
        * DESCRIPTION: updates positions and velocities of Celestials
        * RETURN: none
    */
    private void updateCelestials() throws Exception {
        if(this.celestials.size() < 2) {
            return;
        }
        for(int i = 0; i < this.celestials.size(); i++) {
            Celestial c = this.celestials.get(i);
            c.calculateNetForces(this.celestials);
            c.updateCelestial();
        }
        repaint();
    }
}
