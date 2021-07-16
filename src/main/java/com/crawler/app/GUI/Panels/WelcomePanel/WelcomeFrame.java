package com.crawler.app.GUI.Panels.WelcomePanel;

import javax.swing.JFrame;

import static com.crawler.app.Config.Bounds.*;
import static com.crawler.app.Config.Strings.*;

public class WelcomeFrame extends JFrame {

    public WelcomeFrame() {
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setResizable(false);
        this.setLayout(null);
        this.setTitle(APPLICATION_TITLE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        WelcomePanel welcomePanel = new WelcomePanel(this);
        this.getContentPane().add(welcomePanel);
        welcomePanel.setVisible(true);
    }
}