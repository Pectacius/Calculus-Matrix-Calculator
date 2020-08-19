package ui.panels;

import calculator.model.ExpressionAnswerPair;
import ui.CalculatorAppGUI;
import ui.CustomFonts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlgebraPanel extends CalculatorPanel {
    private JLabel modeDisplay;

    private int memLocation = 0;

    public AlgebraPanel(CalculatorAppGUI app, int width, int length) {
        super(app, width, length);
        initializeMenu();
        initializeButtons();
        initializeMode();
        initializeBackgroundImage(width, length);
    }

    //EFFECTS: sets background image of panel
    private void initializeBackgroundImage(int width, int length) {
        ImageIcon background = new ImageIcon("./data/background.JPG");
        JLabel jlBackgroundImage = new JLabel(background);
        jlBackgroundImage.setBounds(0, 0, width, length);
        this.add(jlBackgroundImage);
    }

    private void initializeButtons() {
        initializeNegativeButton();
        initializeInteractionButtons();
        initializeEnterButton();
    }

    private void initializeMode() {
        modeDisplay = new JLabel();
        this.add(modeDisplay);
        modeDisplay.setText("Mode: Calculate");
        modeDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
        modeDisplay.setFont(CustomFonts.makeFont("NASA", "normal", 15f));
        modeDisplay.setBackground(Color.BLACK);
        modeDisplay.setForeground(Color.WHITE);
        modeDisplay.setBounds(631, 12, DISPLAY_WIDTH / 4, DISPLAY_Y / 2);
    }

    //EFFECTS: initializes dropdown menu
    private void initializeMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");

        this.add(menuBar);
        menuBar.setBounds(0, 0, 40, DISPLAY_Y - 5);
        menuBar.add(menu);
        addMenuItems(menu);
    }

    //EFFECTS: adds two buttons: "viewMemory" and "back" to menu container
    private void addMenuItems(JMenu menu) {
        JMenuItem back = new JMenuItem("Back");
        JMenuItem viewMem = new JMenuItem("View Memory");
        JMenuItem calculate = new JMenuItem("Calculate");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem toBin = new JMenuItem("To Binary");
        JMenuItem delete = new JMenuItem("Delete Memory");

        menu.add(calculate);
        menu.add(viewMem);
        menu.add(toBin);
        menu.add(delete);
        menu.add(save);
        menu.add(back);

        calculate.addActionListener(new CalculateMenuClickHandler());
        viewMem.addActionListener(new ViewMemMenuClickHandler());
        toBin.addActionListener(new ToBinMenuClickHandler());
        delete.addActionListener(new DeleteMemClickHandler());
        save.addActionListener(new SaveMenuClickHandler());
        back.addActionListener(new BackMenuClickHandler());
    }

    private void initializeNegativeButton() {
        JButton negative = new JButton("( - )");
        addButton(negative, COLUMN_TWO,ROW_SIX);
        negative.addActionListener(new InputClickHandler("-", "-"));
    }

    private void initializeInteractionButtons() {
        Font font35 = CustomFonts.makeFont("NASA", "normal", 35);

        JButton next = new JButton("NEXT");
        addButton(next, COLUMN_THREE, ROW_ONE);
        next.setFont(font35);
        next.addActionListener(new NextClickHandler("next"));

        JButton previous = new JButton("PREV");
        addButton(previous, COLUMN_FOUR, ROW_ONE);
        previous.setFont(font35);
        previous.addActionListener(new NextClickHandler("previous"));
    }

    @Override
    protected void initializeEnterButton() {
        JButton enter = new JButton("ENTER");
        addButton(enter,COLUMN_FIVE, ROW_SIX);
        enter.setFont(CustomFonts.makeFont("NASA", "normal", 25));
        enter.addActionListener(new EnterClickHandler());
        enter.setBackground(Color.BLUE);
    }

    private class CalculateMenuClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            isDisplayingMemory = false;
            modeDisplay.setText("Mode: Calculate");
            expression = "";
            calcDisplay.setText(expression);
        }
    }

    private class ViewMemMenuClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            isDisplayingMemory = true;
            modeDisplay.setText("Mode: Display Memory");
            try {
                ExpressionAnswerPair pair = app.getDataAccess().getMemory().get(memLocation);
                expression = "Stored at index 0: " + app.getDataAccess().expressionAnswerPairToString(pair);
            } catch (IndexOutOfBoundsException ex) {
                expression = "No Stored Memory";
            } finally {
                calcDisplay.setText(expression);
            }
        }
    }

    private class DeleteMemClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (isDisplayingMemory) {
                try {
                    app.getDataAccess().getMemory().remove(memLocation);
                    if (!(memLocation == 0)) {
                        memLocation = memLocation - 1;
                    }
                    ExpressionAnswerPair pair = app.getDataAccess().getMemory().get(memLocation);
                    expression = app.getDataAccess().expressionAnswerPairToString(pair);
                    calcDisplay.setText("Stored at index " + memLocation + ": " + expression);
                } catch (IndexOutOfBoundsException ex) {
                    calcDisplay.setText("No Stored Memory");
                }
            }
        }
    }


    private class SaveMenuClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (isDisplayingAnswer && (!isDisplayingMemory)) {
                try {
                    app.getDataAccess().storeValInMemory(expressionToCalculate, expression);
                } catch (NumberFormatException ex) {
                    calcDisplay.setText("This cannot be saved to local memory");
                }
            }
        }
    }

    private class ToBinMenuClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!isDisplayingMemory && isDisplayingAnswer) {
                String binary = calculate.convertToBinary(expression);
                calcDisplay.setText(binary);
            }
        }
    }

    private class NextClickHandler implements ActionListener {
        String command;

        public NextClickHandler(String command) {
            super();
            this.command = command;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (isDisplayingMemory) {
                if (app.getDataAccess().getMemory().size() > 0) {
                    if ((memLocation < app.getDataAccess().getMemory().size() - 1) && (command.equals("next"))) {
                        memLocation = memLocation + 1;
                    } else if ((memLocation > 0) && (command.equals("previous"))) {
                        memLocation = memLocation - 1;
                    }
                    ExpressionAnswerPair pair = app.getDataAccess().getMemory().get(memLocation);
                    expression = app.getDataAccess().expressionAnswerPairToString(pair);
                    calcDisplay.setText("Stored at index " + memLocation + ": " + expression);
                }
            }
        }
    }

    private class EnterClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            isDisplayingAnswer = true;
            try {
                expression = calculate.calculateExpression(expressionToCalculate);
                calcDisplay.setText(expression);
            } catch (NumberFormatException ex) {
                calcDisplay.setText("Sorry.. Cannot calculate.. Try again");
            }
        }
    }
}