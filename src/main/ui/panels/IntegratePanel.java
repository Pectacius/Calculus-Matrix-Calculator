package ui.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EmptyStackException;

public class IntegratePanel extends JPanel {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    String leftVal;
    String rightVal;
    String expressionToCalculate;
    FunctionPanel functionPanel;
    JTextField leftBound;
    JTextField rightBound;

    public IntegratePanel(FunctionPanel functionPanel, String expressionToCalc) {
        this.functionPanel = functionPanel;
        expressionToCalculate = expressionToCalc;
        initializeBackground();
        addTitle();
        addTextBox();
        addButton();
    }

    private void initializeBackground() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    private void addTitle() {
        JLabel question = new JLabel("Set Bounds");
        add(question, BorderLayout.PAGE_START);
    }

    private void addTextBox() {
        leftBound = new JTextField("Change to set Left Bound");
        add(leftBound, BorderLayout.LINE_START);
        rightBound = new JTextField("Change to set Right Bound");
        add(rightBound, BorderLayout.LINE_END);
    }

    private void addButton() {
        JButton okay = new JButton("Okay");
        add(okay, BorderLayout.PAGE_END);
        okay.addActionListener(new ButtonClickHandler());
    }

    private class ButtonClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            leftVal = leftBound.getText();
            rightVal = rightBound.getText();
            try {
                Double result = functionPanel.calculate.integrate(expressionToCalculate,
                        Double.parseDouble(leftVal), Double.parseDouble(rightVal));
                functionPanel.calcDisplay.setText(Double.toString(result));
            } catch (EmptyStackException | NumberFormatException ex) {
                functionPanel.calcDisplay.setText("Sorry cannot calculate");
            }
            functionPanel.getIntegrate().hide();
        }
    }
}
