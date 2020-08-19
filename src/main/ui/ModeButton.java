package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModeButton extends JButton implements ActionListener {
    private final CalculatorAppGUI parent;

    ModeButton(String name, CalculatorAppGUI parent){
        super(name, new ImageIcon("./data/" + name + ".png"));
        this.parent = parent;
        setContentAreaFilled(false);
        setBorderPainted(false);
        setBorder(BorderFactory.createLoweredBevelBorder());
        setHorizontalAlignment(SwingConstants.LEFT);
        addActionListener(this);
        addHoverEffect();
    }

    protected void resetButton(){
        setBorderPainted(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        parent.resetAll();
        setBorderPainted(true);
        parent.showMode(getText());
    }

    private void addHoverEffect(){
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setContentAreaFilled(true);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                setContentAreaFilled(false);
            }
        });
    }
}