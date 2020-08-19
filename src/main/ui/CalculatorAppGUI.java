package ui;

import persistance.CalculatorDA;
import calculator.model.CalculatorBL;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.util.ArrayList;


/**
 * Creates a JFrame to hold the calculator application
 */
public class CalculatorAppGUI extends JFrame{
    private static final int HEIGHT = 800;
    private static final int WIDTH = 1200;

    private NavigatorPane navigator;
    private CalculatorUtilities calculatorModes;


    private final CalculatorDA dataAccess;
    private final CalculatorBL businessLogic;

    //EFFECTS: creates and shows a container package
    public CalculatorAppGUI() {
        super("Calculator App");
        dataAccess = new CalculatorDA();
        businessLogic = new CalculatorBL();
        initializeGraphics();
    }

    //MODIFIES: this
    //EFFECTS: adds various calculation panels and draws this JFrame
    private void initializeGraphics() {
        setFrames();
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: adds the toolbar and editor to the JFrame
    private void setFrames(){
        setLayout(new BorderLayout());
        navigator = new NavigatorPane();
        calculatorModes = new CalculatorUtilities();
        addMode(new Algebra("Algebra", this));
        addMode(new Function("Function", this));
        addMode(new Systems("Systems", this));
        addMode(new Graph("Graph", this));
        add(navigator, BorderLayout.WEST);
        add(calculatorModes, BorderLayout.CENTER);
    }

    public void resetAll(){
        navigator.resetButtons();
    }

    public void showMode(String mode){
        calculatorModes.changeMode(mode);
    }

    private <T extends Mode>void addMode(T t){
        navigator.addButton(t.modeButton);
        calculatorModes.add(t, t.modeButton.getText());
    }

    public void closeApp(){
        this.dispose();
    }

    public CalculatorDA getDataAccess() {
        return dataAccess;
    }

    public CalculatorBL getBusinessLogic() {
        return businessLogic;
    }

    public static void main(String[] args) {
        // run app
        new CalculatorAppGUI();
    }


    private static class CalculatorUtilities extends JPanel {
        private final CardLayout layout = new CardLayout();

        CalculatorUtilities(){
            super();
            setLayout(layout);
        }

        protected void changeMode(String mode){
            layout.show(this, mode);
        }
    }

    private static class NavigatorPane extends JToolBar {
        ArrayList<ModeButton> modes;
        NavigatorPane(){
            super();
            modes = new ArrayList<>();
            setLayout(new GridLayout(12, 0));
            add(createLabel());
            this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.GRAY));
        }

        private JLabel createLabel(){
            JLabel label = new JLabel("MODES", SwingConstants.CENTER);
            label.setFont(new Font("Segoe", Font.BOLD, 20));
            return label;
        }

        protected void addButton(ModeButton button){
            this.modes.add(button);
            this.add(button);
        }

        protected void resetButtons(){
            for (ModeButton button: modes) {
                button.resetButton();
            }
        }
    }
}
