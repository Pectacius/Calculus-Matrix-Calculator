package ui.panels;

import calculator.model.CalculatorBL;
import ui.CalculatorAppGUI;
import ui.CustomFonts;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class CalculatorPanel extends JPanel {
    protected static final int BUTTON_HEIGHT = 100;
    protected static final int BUTTON_WIDTH = 139;
    protected static final int DISPLAY_HEIGHT = 100;
    protected static final int DISPLAY_WIDTH = 775;
    protected static final int DISPLAY_X = 50;
    protected static final int DISPLAY_Y = 50;

    protected static final int COLUMN_ONE = 50;
    protected static final int COLUMN_TWO = 209;
    protected static final int COLUMN_THREE = 368;
    protected static final int COLUMN_FOUR = 527;
    protected static final int COLUMN_FIVE = 686;

    protected static final int ROW_ONE = 170;
    protected static final int ROW_TWO = 274;
    protected static final int ROW_THREE = 378;
    protected static final int ROW_FOUR = 482;
    protected static final int ROW_FIVE = 586;
    protected static final int ROW_SIX = 690;

    protected boolean isDisplayingAnswer;
    protected boolean isDisplayingMemory;

    protected String expression;
    protected String expressionToCalculate;
    protected CalculatorAppGUI app;

    protected JLabel calcDisplay;

    protected CalculatorBL calculate;

    public CalculatorPanel(CalculatorAppGUI app, int width, int length) {
        isDisplayingAnswer = false;
        isDisplayingMemory = false;
        expression = "";
        expressionToCalculate = "";
        this.app = app;
        calculate = new CalculatorBL();

        initializeBackground(width, length);
        initializeButtons();
        initializeDisplay();
    }

    //EFFECTS: sets background of panel
    private void initializeBackground(int width, int length) {
        setLayout(null);
        setPreferredSize(new Dimension(width, length));
    }



    private void initializeButtons() {
        initializeNumberZeroToFourButtons();
        initializeNumberFiveToNineButtons();
        initializeDecimalButton();
        initializeOperatorButtons();
        initializeConstantButtons();
        initializeTranscendentalButtons();
        initializeClearButton();
        initializeEnterButton();
    }

    protected abstract void initializeEnterButton();

    private void initializeNumberZeroToFourButtons() {
        JButton one = new JButton("1");
        addButton(one,COLUMN_TWO,ROW_FIVE);
        one.addActionListener(new InputClickHandler("1", "1"));

        JButton two = new JButton("2");
        addButton(two,COLUMN_THREE,ROW_FIVE);
        two.addActionListener(new InputClickHandler("2", "2"));

        JButton three = new JButton("3");
        addButton(three,COLUMN_FOUR,ROW_FIVE);
        three.addActionListener(new InputClickHandler("3", "3"));

        JButton four = new JButton("4");
        addButton(four,COLUMN_TWO,ROW_FOUR);
        four.addActionListener(new InputClickHandler("4", "4"));

        JButton zero = new JButton("0");
        addButton(zero,COLUMN_THREE,ROW_SIX);
        zero.addActionListener(new InputClickHandler("0", "0"));
    }

    private void initializeNumberFiveToNineButtons() {
        JButton five = new JButton("5");
        addButton(five,COLUMN_THREE,ROW_FOUR);
        five.addActionListener(new InputClickHandler("5", "5"));

        JButton six = new JButton("6");
        addButton(six,COLUMN_FOUR,ROW_FOUR);
        six.addActionListener(new InputClickHandler("6", "6"));

        JButton seven = new JButton("7");
        addButton(seven,COLUMN_TWO,ROW_THREE);
        seven.addActionListener(new InputClickHandler("7", "7"));

        JButton eight = new JButton("8");
        addButton(eight,COLUMN_THREE,ROW_THREE);
        eight.addActionListener(new InputClickHandler("8", "8"));

        JButton nine = new JButton("9");
        addButton(nine,COLUMN_FOUR,ROW_THREE);
        nine.addActionListener(new InputClickHandler("9", "9"));
    }

    private void initializeOperatorButtons() {
        JButton add = new JButton("+");
        addButton(add, COLUMN_FIVE, ROW_FIVE);
        add.addActionListener(new InputClickHandler("+", " + "));

        JButton subtract = new JButton("-");
        addButton(subtract, COLUMN_FIVE, ROW_FOUR);
        subtract.addActionListener(new InputClickHandler("-", " - "));

        JButton multiply = new JButton("*");
        addButton(multiply, COLUMN_FIVE, ROW_THREE);
        multiply.addActionListener(new InputClickHandler("*", " * "));

        JButton divide = new JButton("/");
        addButton(divide, COLUMN_FIVE, ROW_TWO);
        divide.addActionListener(new InputClickHandler("/", " / "));

        JButton exponent = new JButton("^");
        addButton(exponent, COLUMN_FOUR, ROW_TWO);
        exponent.addActionListener(new InputClickHandler("^", " ^ "));

        JButton leftPar = new JButton("(");
        addButton(leftPar, COLUMN_TWO, ROW_TWO);
        leftPar.addActionListener(new InputClickHandler("(", "( "));

        JButton rightPar = new JButton(")");
        addButton(rightPar,COLUMN_THREE, ROW_TWO);
        rightPar.addActionListener(new InputClickHandler(")", " )"));
    }

    private void initializeConstantButtons() {
        JButton pi = new JButton("PI");
        addButton(pi, COLUMN_ONE, ROW_ONE);
        pi.addActionListener(new InputClickHandler("pi", "pi"));

        JButton euler = new JButton("e");
        addButton(euler, COLUMN_TWO, ROW_ONE);
        euler.addActionListener(new InputClickHandler("e", "e"));
    }

    private void initializeTranscendentalButtons() {
        JButton log = new JButton("LOG");
        addButton(log, COLUMN_ONE, 719, 71);
        log.addActionListener(new InputClickHandler("log", "log "));

        JButton sin = new JButton("SIN");
        addButton(sin, COLUMN_ONE, 646, 71);
        sin.addActionListener(new InputClickHandler("sin", "sin "));

        JButton cos = new JButton("COS");
        addButton(cos, COLUMN_ONE, 572, 71);
        cos.addActionListener(new InputClickHandler("cos", "cos "));

        JButton tan = new JButton("TAN");
        addButton(tan, COLUMN_ONE, 499, 71);
        tan.addActionListener(new InputClickHandler("tan", "tan "));

        initializeInverseTrigButtons();
    }

    private void initializeInverseTrigButtons() {
        Font font25 = CustomFonts.makeFont("NASA", "normal", 20);
        JButton arcsin = new JButton("ARCSIN");
        addButton(arcsin, COLUMN_ONE, 425, 71);
        arcsin.setFont(font25);
        arcsin.addActionListener(new InputClickHandler("arcsin", "arcsin "));

        JButton arccos = new JButton("ARCCOS");
        addButton(arccos, COLUMN_ONE, 350, 71);
        arccos.setFont(font25);
        arccos.addActionListener(new InputClickHandler("arccos", "arccos "));

        JButton arctan = new JButton("ARCTAN");
        addButton(arctan, COLUMN_ONE, 275, 71);
        arctan.setFont(font25);
        arctan.addActionListener(new InputClickHandler("arctan", "arctan "));
    }

    protected void addButton(JButton button, int x, int y) {
        this.add(button);
        button.setBounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
        button.setFont(CustomFonts.makeFont("NASA", "normal", 40));
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
    }

    protected void addButton(JButton button, int x, int y, int height) {
        this.add(button);
        button.setBounds(x, y, BUTTON_WIDTH, height);
        button.setFont(CustomFonts.makeFont("NASA", "normal", 40));
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
    }

    private void initializeClearButton() {
        Font font35 = CustomFonts.makeFont("NASA", "normal", 35);
        JButton clear = new JButton("CE");
        addButton(clear, COLUMN_FIVE, ROW_ONE);
        clear.setFont(font35);
        clear.addActionListener(new ClearClickHandler());
    }

    private void initializeDecimalButton() {
        JButton decimal = new JButton(".");
        addButton(decimal, COLUMN_FOUR, ROW_SIX);
        decimal.addActionListener(new InputClickHandler(".", "."));
    }

    //EFFECTS: initializes digital display of calculator
    private void initializeDisplay() {
        Border border = BorderFactory.createLineBorder(Color.WHITE, 5);
        calcDisplay = new JLabel();
        this.add(calcDisplay);
        calcDisplay.setText(expression);
        calcDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
        calcDisplay.setFont(CustomFonts.makeFont("NASA", "normal", 30f));
        calcDisplay.setBackground(Color.BLACK);
        calcDisplay.setForeground(Color.GREEN);
        calcDisplay.setBorder(border);
        calcDisplay.setBounds(DISPLAY_X, DISPLAY_Y, DISPLAY_WIDTH, DISPLAY_HEIGHT);
    }

    protected class InputClickHandler implements ActionListener {
        String displayValue;
        String calculateValue;

        public InputClickHandler(String displayVal, String calculateVal) {
            super();
            this.displayValue = displayVal;
            this.calculateValue = calculateVal;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!isDisplayingMemory && !isDisplayingAnswer) {
                expression += displayValue;
                expressionToCalculate += calculateValue;
            } else if (!isDisplayingMemory) {
                isDisplayingAnswer = false;
                expression = displayValue;
                expressionToCalculate = calculateValue;
            }
            calcDisplay.setText(expression);
        }
    }

    protected class BackMenuClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //app.changeToHome();
        }
    }

    private class ClearClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!isDisplayingMemory) {
                isDisplayingAnswer = false;
                expression = "";
                expressionToCalculate = "";
                calcDisplay.setText(expression);
            }
        }
    }
}
