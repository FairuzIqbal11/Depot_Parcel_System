package Parcel_Processing_System;

import javax.swing.*;
import java.awt.BorderLayout;

/**
 * MainGUI is the primary GUI class for the Parcel Processing System.
 * It defines the layout and placeholder panels for various sections.
 */
public class MainGUI extends JFrame {

    // Instance variables for panels
    private JPanel parcelPanel;
    private JPanel customerPanel;
    private JPanel processingPanel;
    private JPanel controlPanel;

    /**
     * Constructor for MainGUI.
     * Sets up the frame and its components.
     */
    public MainGUI() {
        setTitle("Parcel Processing System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize and add panels
        initializePanels();
        addPanels();
    }

    /**
     * Initializes the panels with titles.
     */
    private void initializePanels() {
        parcelPanel = new JPanel();
        parcelPanel.setBorder(BorderFactory.createTitledBorder("Uncollected Parcels"));

        customerPanel = new JPanel();
        customerPanel.setBorder(BorderFactory.createTitledBorder("Customer Queue"));

        processingPanel = new JPanel();
        processingPanel.setBorder(BorderFactory.createTitledBorder("Currently Processing"));

        controlPanel = new JPanel();
        controlPanel.setBorder(BorderFactory.createTitledBorder("Controls"));
    }

    /**
     * Adds panels to the frame.
     */
    private void addPanels() {
        add(parcelPanel, BorderLayout.WEST);
        add(customerPanel, BorderLayout.CENTER);
        add(processingPanel, BorderLayout.EAST);
        add(controlPanel, BorderLayout.SOUTH);
    }

    /**
     * Entry point of the application.
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        MainGUI gui = new MainGUI();
        gui.setVisible(true);
    }
}
