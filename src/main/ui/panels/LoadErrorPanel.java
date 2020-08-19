package ui.panels;

import ui.CustomFonts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadErrorPanel extends JPanel {
    private static int WIDTH = 357;
    private static int HEIGHT = 235;
    private static int MSG_WIDTH = 170;
    private static int MSG_HEIGHT = 100;

    HomePanel homePanel;

    public LoadErrorPanel(HomePanel homePanel) {
        this.homePanel = homePanel;
        initializeBackground();
        initializeButton();
        setLabel();
        setBackgroundImage();
    }

    private void setLabel() {
        JLabel errorMSG = new JLabel("<html>This is not the file <br> you are looking for..<html>");
        errorMSG.setBounds(MSG_WIDTH, MSG_HEIGHT, MSG_WIDTH, MSG_HEIGHT);
        errorMSG.setFont(CustomFonts.makeFont("exan", "normal", 15f));
        errorMSG.setForeground(Color.BLACK);
        this.add(errorMSG);
    }

    private void setBackgroundImage() {
        ImageIcon background = new ImageIcon("./data/LoadErrorIMG.JPG");
        JLabel jlBackgroundImage = new JLabel(background);
        jlBackgroundImage.setBounds(0, 0, WIDTH, HEIGHT);
        this.add(jlBackgroundImage);
    }


    private void initializeButton() {
        JButton close = new JButton("Close");
        close.setBounds(WIDTH - 80, 0, 80, 30);
        close.setFont(CustomFonts.makeFont("exan", "normal", 15f));
        close.setBackground(Color.WHITE);
        close.setForeground(Color.RED);
        close.addActionListener(new CloseButtonHandler());
        this.add(close);
        close.setFocusPainted(false);
    }

    private void initializeBackground() {
        setLayout(null);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    private class CloseButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            homePanel.getLoadError().hide();
        }
    }
}
