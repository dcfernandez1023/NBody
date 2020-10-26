package NBody.CelestialView;

import NBody.CelestialController.CelestialDataParser;
import NBody.CelestialModel.Celestial;
import NBody.CelestialModel.Container;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CelestialDrawer extends JPanel implements ActionListener {
    private int xPixels;
    private int yPixels;
    private Container<Object> celestials;
    private Container<Object> metaData;
    private Timer timer = new Timer(5, this);

    private float currentInitCoordX;
    private float currentInitDirectionX;

    public CelestialDrawer(int xPixels, int yPixels, String fileName) throws Exception {
        CelestialDataParser cdp = new CelestialDataParser(fileName);
        this.celestials = cdp.getCelestialRecords();
        this.metaData = cdp.getCelestialMetaData();
    }

    @Override
    public void paintComponent(Graphics g) {
        /*
        super.paintComponent(g);
        Celestial c = (Celestial) this.celestials.get(1);
        float initCoordX = Float.parseFloat((String) c.getValue("initCoordX"));
        float initDirectionX = Float.parseFloat((String) c.getValue("initDirectionX"));
        this.currentInitCoordX = initCoordX;
        this.currentInitDirectionX = initDirectionX;
        System.out.println(this.currentInitCoordX);
        System.out.println(this.currentInitDirectionX);
        g.setColor(Color.RED);
        g.fillOval((int) this.currentInitCoordX, 30, 30, 30);
        this.timer.start();

         */

        super.paintComponent(g);
        for(int i = 0; i < this.celestials.size(); i++) {
            Celestial c = (Celestial) this.celestials.get(i);
            float initCoordX = Float.parseFloat((String) c.getValue("initCoordX"));
            System.out.println(c.getValue("name") + " " + Float.toString(initCoordX));
            g.fillOval((int) initCoordX, 30, 30, 30);
            g.setColor(Color.RED);
        }
        this.timer.start();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < this.celestials.size(); i++) {
            Celestial c = (Celestial) this.celestials.get(i);
            float initCoordX = Float.parseFloat((String) c.getValue("initCoordX"));
            float initDirectionX = Float.parseFloat((String) c.getValue("initDirectionX"));
            initCoordX = initCoordX + initDirectionX;
            try {
                c.updateValue("initCoordX", Float.toString(initCoordX));
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        repaint();
    }
}
