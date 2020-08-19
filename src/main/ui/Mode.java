package ui;

import javax.swing.*;
import java.awt.*;

public abstract class Mode extends JPanel {
    protected ModeButton modeButton;

    Mode(String name, CalculatorAppGUI parent){
        super();
        setFont(new Font("Segoe", Font.BOLD, 20));
        modeButton = new ModeButton(name, parent);
    }
}
