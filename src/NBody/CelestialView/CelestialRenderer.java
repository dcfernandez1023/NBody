package NBody.CelestialView;
/*
    This class is the entry point for the UI. It renders the proper panels for the user to see and interact with.
*/
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class CelestialRenderer {
    private String fileName; //file to read
    private int windowXPixels; //size of window
    private int windowYPixels; //size of window
    private JFrame frame; //the main frame
    private JPanel panel; //the main panel

    /*
        * PARAMS: String fileName - the name of the file to be read
        * DESCRIPTION: constructs a new CelestialRenderer and sets data members (default xpixels and ypixels is 768x768)
        * RETURN: none
    */
    public CelestialRenderer(String fileName) {
        this.windowXPixels = 768;
        this.windowYPixels = 768;
        this.fileName = fileName;
    }
    /*
        * PARAMS: none
        * DESCRIPTION: this method begins the entire application. It first renders a welcome screen where the user can change which file will be read,
                       and also allows the user to customize the size of the screen where the Celestials are to be rendered
        * RETURN: none
    */
    public void render() {
        try {
            this.renderWelcomeScreen(); //render welcome screen -- this will render Celestials once "Begin" button is pressed
        }
        catch(Exception e) {
            //display error panel on any exceptions that are thrown
            this.clearMainPanel();
            this.renderErrorPanel(e);
        }
    }
    /*
        * PARAMS: none
        * DESCRIPTION: renders welcome screen and a button to render the Celestials moving
        * RETURN: none
    */
    private void renderWelcomeScreen() throws Exception {
        //ALL THE CODE BELOW IS JUST RENDERING UI COMPONENTS ONTO THE JFRAME
        this.frame = new JFrame("CS 245 NBody - Welcome Screen");
        this.panel = new JPanel();
        this.frame.setContentPane(this.panel);
        this.frame.setSize(500, 500);
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
        //using simple box-layout to place UI components on one line at a time
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

        //Begin button with its action listener to render the CelestialDrawer object
        JPanel buttonPanel = new JPanel();
        JButton beginButton = new JButton("Begin");
        beginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearMainPanel(); //clear main panel first before rendering anything
                getCelestialWindowPixels(xField, yField); //get user inputs for x and y pixel size
                getFileNameInput(fileField); //get user input for filename
                try {
                    renderCelestials(); //render CelestialDrawer
                }
                catch (Exception ex) {
                    //render error panel on exceptions thrown
                    clearMainPanel();
                    renderErrorPanel(ex);
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
    /*
        * PARAMS: none
        * DESCRIPTION: instantiates and renders CelestialDrawer object, which displays all the Celestials moving
        * RETURN: none
    */
    private void renderCelestials() throws Exception {
        //instantiate and add CelestialDrawer to the JFrame
        this.frame.getContentPane().add(new CelestialDrawer(this.windowXPixels, this.windowYPixels, this.fileName));
        this.frame.setTitle("Celestial Renderer " + this.windowXPixels + " x " + this.windowYPixels);
        this.frame.setSize(this.windowXPixels, this.windowYPixels);
        this.panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.frame.revalidate();
    }
    //method to clear the JFrame so that new UI components can be rendered on it
    private void clearMainPanel() {
        this.frame.getContentPane().removeAll();
        this.frame.repaint();
    }
    //method to get filename from input field on welcome screen
    private void getFileNameInput(JTextField inputField) {
        this.fileName = inputField.getText();
    }
    //method to get x and y pixels from input fields on welcome screen
    private void getCelestialWindowPixels(JTextField inputFieldX, JTextField inputFieldY) {
        int x;
        int y;
        try {
            x = Integer.parseInt(inputFieldX.getText());
            y = Integer.parseInt(inputFieldY.getText());
        }
        catch(NumberFormatException nfe) {
            x = 768;
            y = 768;
        }
        if(x <= 0) {
            x = 768;
        }
        if(y <= 0) {
            y = 768;
        }
        this.windowXPixels = x;
        this.windowYPixels = y;
    }
    //method to render error panel
    private void renderErrorPanel(Exception e) {
        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
        this.panel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
        JPanel panel = new JPanel();
        JLabel errorPrompt = new JLabel("Oops! An error occurred: ");
        JLabel errorMessage = new JLabel(e.toString());
        panel.add(errorPrompt);
        panel.add(errorMessage);
        this.panel.add(panel);
        this.frame.revalidate();
    }
}

