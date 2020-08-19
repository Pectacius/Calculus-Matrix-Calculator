package ui.panels;

import ui.CalculatorAppGUI;
import ui.CustomFonts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FunctionPanel extends CalculatorPanel {

    private Popup evaluate;
    private Popup integrate;

    public FunctionPanel(CalculatorAppGUI app, int width, int length) {
        super(app, width, length);
        initializeMenu();
        initializeButtons();
        calcDisplay.setFont(CustomFonts.makeFont("NASA", "normal", 15f));
        initializeBackgroundImage(width, length);
    }

    //EFFECTS: sets background image of panel
    private void initializeBackgroundImage(int width, int length) {
        ImageIcon background = new ImageIcon("./data/background.JPG");
        JLabel jlBackgroundImage = new JLabel(background);
        jlBackgroundImage.setBounds(0, 0, width, length);
        this.add(jlBackgroundImage);
    }

    private void initializeMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");

        this.add(menuBar);
        menuBar.setBounds(0, 0, 40, DISPLAY_Y - 5);
        menuBar.add(menu);
        addMenuItems(menu);
    }

    private void addMenuItems(JMenu menu) {
        JMenuItem back = new JMenuItem("Back");
        JMenuItem evaluate = new JMenuItem("Evaluate f(x)");
        JMenuItem differentiate = new JMenuItem("f'(x)");
        JMenuItem integrate = new JMenuItem("Definite integral");
        //JMenuItem graph = new JMenuItem("Graph");


        menu.add(evaluate);
        menu.add(differentiate);
        menu.add(integrate);
        //menu.add(graph);
        menu.add(back);

        back.addActionListener(new BackMenuClickHandler());
        evaluate.addActionListener(new EvaluateInputHandler());
        differentiate.addActionListener(new DifferentiateInputHandler());
        integrate.addActionListener(new IntegrateInputHandler());
    }

    private void initializeButtons() {
        initializeEnterButton();
        initializeVariableButton();
    }

    private void initializeVariableButton() {
        JButton variable = new JButton("X");
        addButton(variable, COLUMN_TWO,ROW_SIX);
        variable.addActionListener(new InputClickHandler("X", "x"));
    }

    private void createEvaluatePopUp() {
        PopupFactory pf = new PopupFactory();
        EvaluatePanel evaluatePanel = new EvaluatePanel(this, expressionToCalculate);
        this.add(evaluatePanel);
        evaluate = pf.getPopup(app,evaluatePanel, 237, 400);
    }

    private void createIntegratePopUp() {
        PopupFactory pf = new PopupFactory();
        IntegratePanel integratePanel = new IntegratePanel(this, expressionToCalculate);
        this.add(integratePanel);
        integrate = pf.getPopup(app,integratePanel, 237, 400);
    }

    public Popup getEvaluate() {
        return evaluate;
    }

    public Popup getIntegrate() {
        return integrate;
    }

    @Override
    protected void initializeEnterButton() {
        JButton enter = new JButton("ENTER");
        addButton(enter,COLUMN_FIVE, ROW_SIX);
        enter.setFont(CustomFonts.makeFont("NASA", "normal", 25));
        enter.setBackground(Color.BLUE);
    }

    private class DifferentiateInputHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String derivative = calculate.differentiate(expressionToCalculate);
            expressionToCalculate = derivative;
            expression = derivative.replaceAll("\\s+","");
            calcDisplay.setText(expression);
        }
    }

    private class EvaluateInputHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            createEvaluatePopUp();
            evaluate.show();
        }
    }

    private class IntegrateInputHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            createIntegratePopUp();
            integrate.show();
        }
    }
}
