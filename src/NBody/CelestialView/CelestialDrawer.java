package NBody.CelestialView;

import NBody.CelestialController.CelestialDataParser;
import NBody.CelestialModel.Celestial;
import NBody.CelestialModel.Container;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CelestialDrawer extends JPanel implements ActionListener {
    private static final double GC = 6.673e-11;
    private double scaleFactor;
    private int xPixels;
    private int yPixels;
    private Container<Object> celestials;
    private Container<Object> metaData;
    private Timer timer = new Timer(5, this);

    public CelestialDrawer(int xPixels, int yPixels, String fileName) throws Exception {
        CelestialDataParser cdp = new CelestialDataParser(fileName);
        this.celestials = cdp.getCelestialRecords();
        this.metaData = cdp.getCelestialMetaData();
        this.scaleFactor = Double.parseDouble((String) this.metaData.get(1));
        this.xPixels = xPixels;
        this.yPixels = yPixels;
        this.setSize(xPixels, yPixels);
        Border border = BorderFactory.createLineBorder(Color.black);
        this.setBorder(border);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int i = 0; i < this.celestials.size(); i++) {
            Celestial c = (Celestial) this.celestials.get(i);
            double x = c.getX();
            double y = c.getY();
            double size = c.getSize();
            g.fillOval((int) x, (int) y, (int) size, (int) size);
            g.setColor(Color.RED);
        }
        this.timer.start();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try
        {
            this.updateCelestials();
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    private void updateCelestials() throws Exception
    {
        for(int i = 0; i < this.celestials.size(); i++) {
            Celestial c = (Celestial) this.celestials.get(i);
            for(int j = 0; j < this.celestials.size(); j++) {
                c.calculateNetForces(this.celestials);
                c.updateCelestial();
                if(c.getX() < 0 || c.getX() > this.xPixels) {
                    this.celestials.remove(i);
                    break;
                }
                if(c.getY() < 0 || c.getX() > this.yPixels) {
                    this.celestials.remove(i);
                    break;
                }
            }
        }
        repaint();
    }
}
