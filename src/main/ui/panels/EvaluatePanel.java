package ui.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EmptyStackException;

public class EvaluatePanel extends JPanel {
    private static final int WIDTH = 200;
    private static final int HEIGHT = 200;
    String val;
    String expressionToCalculate;
    FunctionPanel functionPanel;
    JTextField submission;

    public EvaluatePanel(FunctionPanel functionPanel, String expressionToCalc) {
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
        JLabel question = new JLabel("Evaluate At");
        add(question, BorderLayout.PAGE_START);
    }

    private void addTextBox() {
        submission = new JTextField();
        add(submission, BorderLayout.CENTER);
    }

    private void addButton() {
        JButton okay = new JButton("Okay");
        add(okay, BorderLayout.PAGE_END);
        okay.addActionListener(new ButtonClickHandler());
    }

    private class ButtonClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            val = submission.getText();
            String displayVal;
            try {
                Double result = functionPanel.calculate.evaluateFunction(expressionToCalculate, val);
                displayVal = Double.toString(result);
            } catch (EmptyStackException | NumberFormatException ex) {
                displayVal = "Sorry Cannot Evaluate";
            }
            functionPanel.calcDisplay.setText(displayVal);
            functionPanel.getEvaluate().hide();
        }
    }
}
