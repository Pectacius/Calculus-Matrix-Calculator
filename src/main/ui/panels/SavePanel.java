package ui.panels;

import ui.CalculatorAppGUI;
import ui.CustomFonts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SavePanel extends JPanel {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 250;
    private static final int BUTTON_SIZE_Y = HEIGHT / 4;
    private static final int BUTTON_SIZE_X = 100;
    private static final int BUTTON_LOCATION_Y = 150;
    private static final int NO_BUTTON_X = 230;
    private static final int YES_BUTTON_X = 66;
    private static final int LABEL_SIZE_Y = 100;
    private static final int TITLE_LOCATION_X = 50;
    private static final int TITLE_LOCATION_Y = 10;
    private static final int QUESTION_LOCATION_X = 70;
    private static final int QUESTION_LOCATION_Y = 60;

    protected CalculatorAppGUI app;

    public SavePanel(CalculatorAppGUI app) {
        this.app = app;
        initializeBackground();
        initializeButtons();
        initializeLabels();
    }

    //MODIFIES:this
    //EFFECTS: creates a new panel with a label
    private void initializeBackground() {
        setLayout(null);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setBorder(BorderFactory.createLineBorder(Color.WHITE));
    }

    private void initializeLabels() {
        int size = app.getDataAccess().getMemory().size();
        JLabel title = new JLabel("You have " + size + " calculations in Memory");
        JLabel saveQuestion = new JLabel("Save all previous calculations");
        add(title);
        add(saveQuestion);
        title.setBounds(TITLE_LOCATION_X, TITLE_LOCATION_Y, WIDTH, LABEL_SIZE_Y);
        saveQuestion.setBounds(QUESTION_LOCATION_X, QUESTION_LOCATION_Y, WIDTH, LABEL_SIZE_Y);
        title.setFont(CustomFonts.makeFont("exan", "normal", 15f));
        title.setForeground(Color.GREEN);
        saveQuestion.setFont(CustomFonts.makeFont("exan", "normal", 15f));
        saveQuestion.setForeground(Color.GREEN);
    }

    //EFFECTS: creates a new "yes" button and a new "no" button.
    private void initializeButtons() {
        initializeYesButton();
        initializeNoButton();
    }

    //EFFECTS: creates a red "no" button
    private void initializeNoButton() {
        JButton no = new JButton("No");
        add(no);
        no.setBounds(NO_BUTTON_X, BUTTON_LOCATION_Y, BUTTON_SIZE_X, BUTTON_SIZE_Y);
        no.setBackground(Color.RED);
        no.setFont(CustomFonts.makeFont("exan", "normal", 25f));
        no.addActionListener(new NoButtonHandler());
    }

    //EFFECTS: creates a green "yes" button
    private void initializeYesButton() {
        JButton yes = new JButton("Yes");
        add(yes);
        yes.setBounds(YES_BUTTON_X, BUTTON_LOCATION_Y, BUTTON_SIZE_X, BUTTON_SIZE_Y);
        yes.setBackground(Color.GREEN);
        yes.setFont((CustomFonts.makeFont("exan", "normal", 25f)));
        yes.addActionListener(new YesButtonHandler());
    }

    //Button handler for "yes" button
    private class YesButtonHandler implements ActionListener {
        //MODIFIES: app
        //EFFECTS: saves all stored calculations to file and closes frame
        @Override
        public void actionPerformed(ActionEvent e) {
            app.getDataAccess().saveMemory();
            app.closeApp();
        }
    }

    //Button handler for "no" button
    private class NoButtonHandler implements ActionListener {
        //MODIFIES: app
        //EFFECTS: closes frame
        @Override
        public void actionPerformed(ActionEvent e) {
            app.closeApp();
        }
    }
}
