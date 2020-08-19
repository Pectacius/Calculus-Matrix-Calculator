package ui.panels;

import org.xml.sax.SAXException;
import ui.CalculatorAppGUI;
import ui.CustomFonts;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class HomePanel extends JPanel {
    private static final int EXPRESSION_BUTTON_LOCATION_Y = 325;
    private static final int FUNCTION_BUTTON_LOCATION_Y = 465;
    private static final int LOAD_BUTTON_LOCATION_Y = 605;
    private static final int QUIT_BUTTON_LOCATION_Y = 745;

    private static final int BUTTON_LOCATION_X = 146;
    private static final int BUTTON_SIZE_X = 580;
    private static final int BUTTON_SIZE_Y = 110;

    private Popup save;
    private Popup loadError;

    protected CalculatorAppGUI app;

    private List<JButton> buttons;

    public HomePanel(CalculatorAppGUI app, int width, int length) {
        this.app = app;
        buttons = new ArrayList<>();
        initializeBackground(width, length);
        initializeButtons();
        setBackgroundImage(width, length);
    }

    //EFFECTS: changes all button font color to green, background to black and font to custom font "buttonFont"
    private void formatButtons() {
        for (JButton button: buttons) {
            button.setFont(CustomFonts.makeFont("exan", "normal",40f));
            button.setBackground(Color.BLACK);
            button.setForeground(Color.GREEN);
            button.setFocusPainted(false);
        }
    }

    //EFFECTS: adds all buttons in buttons list to container.
    private void addButtons() {
        for (JButton button: buttons) {
            this.add(button);
        }
    }

    //EFFECTS: creates a save option popup
    private void createSavePopUp() {
        PopupFactory pf = new PopupFactory();
        SavePanel savePanel = new SavePanel(app);
        this.add(savePanel);
        save = pf.getPopup(app,savePanel, 237, 400);
    }

    //EFFECTS: creates a loadError popup with image :)
    private void createLoadErrorPopUp() {
        PopupFactory pf = new PopupFactory();
        LoadErrorPanel loadErrorPanel = new LoadErrorPanel(this);
        this.add(loadErrorPanel);
        loadError = pf.getPopup(app, loadErrorPanel, 270, 270);
    }

    private void initializeBackground(int width, int length) {
        setLayout(null);
        setPreferredSize(new Dimension(width, length));
    }


    //EFFECTS: sets the background image of panel
    private void setBackgroundImage(int width, int length) {
        ImageIcon background = new ImageIcon("./data/background.JPG");
        JLabel jlBackgroundImage = new JLabel(background);
        jlBackgroundImage.setBounds(0, 0, width, length);
        this.add(jlBackgroundImage);
    }

    //EFFECTS: initializes four buttons: expression, function, load and quit
    private void initializeButtons() {
        JButton expression = new JButton("Evaluate Expression");
        buttons.add(expression);
        expression.setBounds(BUTTON_LOCATION_X, EXPRESSION_BUTTON_LOCATION_Y, BUTTON_SIZE_X, BUTTON_SIZE_Y);
        expression.addActionListener(new ExpressionClickHandler());


        JButton function = new JButton("Evaluate Function");
        buttons.add(function);
        function.setBounds(BUTTON_LOCATION_X, FUNCTION_BUTTON_LOCATION_Y, BUTTON_SIZE_X, BUTTON_SIZE_Y);
        function.addActionListener(new FunctionClickHandler());

        JButton load = new JButton("Load");
        buttons.add(load);
        load.setBounds(BUTTON_LOCATION_X, LOAD_BUTTON_LOCATION_Y, BUTTON_SIZE_X, BUTTON_SIZE_Y);
        load.addActionListener(new LoadButtonClickHandler());


        JButton quit = new JButton("Quit");
        buttons.add(quit);
        quit.setBounds(BUTTON_LOCATION_X, QUIT_BUTTON_LOCATION_Y, BUTTON_SIZE_X, BUTTON_SIZE_Y);
        quit.addActionListener(new QuitButtonHandler());

        addButtons();
        formatButtons();
    }

    public Popup getLoadError() {
        return loadError;
    }


    private class ExpressionClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //app.changeToExpression();
        }
    }

    private class FunctionClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //app.changeToFunction();
        }
    }

    private class LoadButtonClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                app.getDataAccess().loadSaveState();
            } catch (ParserConfigurationException | IOException | SAXException | NullPointerException ex) {
                createLoadErrorPopUp();
                loadError.show();
            }
        }
    }

    private class QuitButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            createSavePopUp();
            save.show();
        }
    }
}

