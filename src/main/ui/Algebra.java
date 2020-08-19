package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Algebra extends Mode {
    MemoryPane memoryPane;
    AlgebraPane algebraPane;
    String expression = "";
    CalculatorAppGUI gui;
    Boolean secondFunction = false;
    ArrayList<TrigButton> TrigButtons = new ArrayList<>();

    Algebra(String name, CalculatorAppGUI parent) {
        super(name, parent);
        gui = parent;
        setLayout(new BorderLayout());
        setDisplay();
        setMemoryPane();
    }

    private void setDisplay(){
        algebraPane = new AlgebraPane();
        add(algebraPane, BorderLayout.CENTER);
    }

    private void setMemoryPane() {
        memoryPane = new MemoryPane();
        add(memoryPane, BorderLayout.EAST);
    }

    private class AlgebraPane extends JPanel {
        JLabel display;
        JPanel calculatorPanel = new JPanel(new BorderLayout());

        AlgebraPane() {
            super(new BorderLayout());
            initializeDisplay();
            initializeMainButtons();
            initializeTranscendentalButtons();
            initializeUtilityButtons();
            calculatorPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            add(calculatorPanel);
        }

        private void initializeDisplay(){
            display = new JLabel("GREETINGS");
            display.setBorder(BorderFactory.createLineBorder(Color.GRAY, 5));
            display.setHorizontalAlignment(SwingConstants.RIGHT);
            display.setPreferredSize(new Dimension(400, 100));
            add(display, BorderLayout.NORTH);
        }

        private void initializeMainButtons() {
            JPanel buttonPanel = new JPanel(new GridLayout(5, 4));
            buttonPanel.setPreferredSize(new Dimension(636, 580));
            buttonPanel.add(new CalculatorButton("(", new GenericValueClickHandler("( ")));
            buttonPanel.add(new CalculatorButton(")", new GenericValueClickHandler(" )")));
            buttonPanel.add(new CalculatorButton("^", new GenericValueClickHandler(" ^ ")));
            buttonPanel.add(new CalculatorButton("/", new GenericValueClickHandler(" / ")));

            buttonPanel.add(new CalculatorButton("7", new GenericValueClickHandler("7")));
            buttonPanel.add(new CalculatorButton("8", new GenericValueClickHandler("8")));
            buttonPanel.add(new CalculatorButton("9", new GenericValueClickHandler("9")));
            buttonPanel.add(new CalculatorButton("*", new GenericValueClickHandler(" * ")));

            buttonPanel.add(new CalculatorButton("4", new GenericValueClickHandler("4")));
            buttonPanel.add(new CalculatorButton("5", new GenericValueClickHandler("5")));
            buttonPanel.add(new CalculatorButton("6", new GenericValueClickHandler("6")));
            buttonPanel.add(new CalculatorButton("-", new GenericValueClickHandler("-")));

            buttonPanel.add(new CalculatorButton("1", new GenericValueClickHandler("1")));
            buttonPanel.add(new CalculatorButton("2", new GenericValueClickHandler("2")));
            buttonPanel.add(new CalculatorButton("3", new GenericValueClickHandler("3")));
            buttonPanel.add(new CalculatorButton("+", new GenericValueClickHandler("+")));

            buttonPanel.add(new CalculatorButton("( - )", new GenericValueClickHandler("-")));
            buttonPanel.add(new CalculatorButton("0", new GenericValueClickHandler("0")));
            buttonPanel.add(new CalculatorButton(".", new GenericValueClickHandler(".")));
            buttonPanel.add(new CalculatorButton("Enter", new EvaluateClickHandler()));
            calculatorPanel.add(buttonPanel, BorderLayout.CENTER);
        }

        private void initializeTranscendentalButtons() {
            JPanel transcendentalPanel = new JPanel(new GridLayout(5, 1));
            transcendentalPanel.setPreferredSize(new Dimension(159, 635));

            transcendentalPanel.add(new CalculatorButton("2nd", new SwitchMode()));

            transcendentalPanel.add(new TrigButton("sin", "arcsin",
                    new SecondFunctionClickHandler("sin ", "arcsin ")));
            transcendentalPanel.add(new TrigButton("cos", "arccos",
                    new SecondFunctionClickHandler("cos ", "arccos ")));
            transcendentalPanel.add(new TrigButton("tan", "arctan",
                    new SecondFunctionClickHandler("tan ", "arctan ")));

            transcendentalPanel.add(new CalculatorButton("log", new GenericValueClickHandler("log ")));

            transcendentalPanel.setBackground(Color.GRAY);
            calculatorPanel.add(transcendentalPanel, BorderLayout.WEST);
        }

        private void initializeUtilityButtons() {
            JPanel utilityPanel = new JPanel(new GridLayout(1, 4));
            utilityPanel.setPreferredSize(new Dimension(796,127));

            utilityPanel.add(new CalculatorButton("SAVE", new SaveClickHandler()));

            utilityPanel.add(new CalculatorButton("\u03C0", new GenericValueClickHandler("pi")));
            utilityPanel.add(new CalculatorButton("\u2107", new GenericValueClickHandler("e")));

            utilityPanel.add(new CalculatorButton("\u2190", new BackClickHandler()));
            utilityPanel.add(new CalculatorButton("CE", new ClearClickHandler()));

            utilityPanel.setBackground(Color.GRAY);
            calculatorPanel.add(utilityPanel, BorderLayout.NORTH);
        }
    }

    private static class CalculatorButton extends JButton {
        CalculatorButton(String displayValue, ActionListener clickHandler) {
            super(displayValue);
            setFont(new Font("Segoe", Font.BOLD, 20));
            addActionListener(clickHandler);
            setOpaque(false);
            setContentAreaFilled(false);
        }
    }

    private class TrigButton extends CalculatorButton {
        String firstValue;
        String secondValue;
        TrigButton(String firstValue, String secondValue, ActionListener clickHandler) {
            super(firstValue, clickHandler);
            this.firstValue = firstValue;
            this.secondValue = secondValue;
            TrigButtons.add(this);
        }

        protected void switchDisplay() {
            if (secondFunction){
                this.setText(secondValue);
            } else {
                this.setText(firstValue);
            }
        }
    }

    private class SecondFunctionClickHandler implements ActionListener {
        String firstValue;
        String secondValue;
        SecondFunctionClickHandler(String firstValue, String secondValue) {
            this.firstValue = firstValue;
            this.secondValue = secondValue;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (secondFunction) {
                expression = expression + secondValue;
            } else {
                expression = expression + firstValue;
            }
        }
    }

    private class SwitchMode implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            secondFunction = (! secondFunction);
            for (TrigButton button: TrigButtons) {
                button.switchDisplay();
            }
        }
    }

    private class SaveClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(algebraPane.getSize().height);
            System.out.println(algebraPane.getSize().width);
        }
    }

    private class EvaluateClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                expression = gui.getBusinessLogic().calculateExpression(expression);
                algebraPane.display.setText(expression);
            } catch (NumberFormatException ex) {
                algebraPane.display.setText("Oops.. Seems Like There Is An Error In Your Value O.O! .. Try again");
            }
        }
    }

    private class GenericValueClickHandler implements ActionListener {
        String value;

        GenericValueClickHandler(String value) {
            this.value = value;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            expression = expression + this.value;
            algebraPane.display.setText(expression);
        }
    }

    private class BackClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if ((expression != null) && (expression.length() > 0)) {
                expression = expression.substring(0, expression.length() - 1);
                algebraPane.display.setText(expression);
            }
        }
    }

    private class ClearClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            expression = "";
            algebraPane.display.setText(expression);
        }
    }

    private class MemoryPane extends JPanel {
        ArrayList<MemoryEntry> memoryButtons = new ArrayList<>();
        JScrollBar scrollBar;

        MemoryPane(){
            super(new BorderLayout());
            addTitle();
            addScrollDisplay();
            setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            setPreferredSize(new Dimension(300, 800));
            setVisible(true);
        }

        private void addTitle() {
            JLabel title = new JLabel("MEMORY");
            title.setFont(new Font("Segoe", Font.BOLD, 16));
            title.setPreferredSize(new Dimension(300, 50));
            title.setHorizontalAlignment(SwingConstants.CENTER);
            add(title, BorderLayout.NORTH);
        }

        private void addScrollDisplay() {
            scrollBar = new JScrollBar();
            scrollBar.setPreferredSize(new Dimension(300,700 ));
            add(scrollBar, BorderLayout.CENTER);
        }

        protected void deleteMemory(MemoryEntry entry) {
            memoryButtons.remove(entry);
            updateEntries();
        }

        protected void updateEntries(){
            scrollBar.removeAll();
            for (MemoryEntry entry: memoryButtons) {
                scrollBar.add(entry);
            }
            scrollBar.revalidate();
            scrollBar.repaint();
        }
    }

    private class MemoryEntry extends JLabel implements ActionListener {
        JButton delete = new JButton();
        MemoryPane parent;

        MemoryEntry(String answer, MemoryPane parent) {
            super(answer);
            this.parent = parent;
            delete.addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            parent.deleteMemory(this);
        }
    }
}
