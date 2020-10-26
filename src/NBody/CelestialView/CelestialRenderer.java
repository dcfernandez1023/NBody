package NBody.CelestialView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import NBody.CelestialModel.Container;

public class CelestialRenderer {
    private String fileName;
    private int windowXPixels;
    private int windowYPixels;
    private JFrame frame;
    private JPanel panel;

    public CelestialRenderer(Container<Object> celestials, Container<Object> metaData, String fileName) {
        this.windowXPixels = 768;
        this.windowYPixels = 768;
        this.fileName = fileName;
    }
    public void render() {
        this.renderWelcomeScreen();
    }
    private void renderWelcomeScreen() {
        this.frame = new JFrame("CS 245 NBody - Welcome Screen");
        this.panel = new JPanel();
        this.frame.setContentPane(this.panel);
        this.frame.setSize(400, 400);
        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
        this.panel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        JLabel header = new JLabel("CS 245 NBody Assignment");
        JLabel by = new JLabel("By: Dominic Fernandez");
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        by.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(header);
        headerPanel.add(by);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

        JPanel filePanel = new JPanel();
        JLabel fileLabel = new JLabel("Celestial File Name: ");
        JTextField fileField = new JTextField(this.fileName, 20);
        filePanel.add(fileLabel);
        filePanel.add(fileField);

        JPanel pixelPanel = new JPanel();
        JLabel pixelLabel = new JLabel("Size of Celestial Renderer: ");
        JTextField xField = new JTextField(Integer.toString(this.windowXPixels), 5);
        JLabel xLabel = new JLabel(" x ");
        JTextField yField = new JTextField(Integer.toString(this.windowYPixels), 5);
        pixelPanel.add(pixelLabel);
        pixelPanel.add(xField);
        pixelPanel.add(xLabel);
        pixelPanel.add(yField);

        inputPanel.add(filePanel);
        inputPanel.add(pixelPanel);

        JPanel buttonPanel = new JPanel();
        JButton beginButton = new JButton("Begin");
        beginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearMainPanel();
                getCelestialWindowPixels(xField, yField);
                getFileNameInput(fileField);
                try {
                    renderCelestials();
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        buttonPanel.add(beginButton);

        this.panel.add(headerPanel);
        this.panel.add(inputPanel);
        this.panel.add(buttonPanel);
        this.frame.setVisible(true);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private void renderCelestials() throws Exception {
        this.frame.getContentPane().add(new CelestialDrawer(this.windowXPixels, this.windowYPixels, this.fileName));
        this.frame.setTitle("Celestial Renderer " + this.windowXPixels + " x " + this.windowYPixels);
        this.frame.revalidate();
    }
    private void clearMainPanel() {
        this.frame.getContentPane().removeAll();
        this.frame.repaint();
    }
    private void getFileNameInput(JTextField inputField) {
        this.fileName = inputField.getText();
    }
    private void getCelestialWindowPixels(JTextField inputFieldX, JTextField inputFieldY) {
        int x = Integer.parseInt(inputFieldX.getText());
        int y = Integer.parseInt(inputFieldY.getText());
        this.windowXPixels = x;
        this.windowYPixels = y;
        this.frame.setSize(x,y);
    }
}

