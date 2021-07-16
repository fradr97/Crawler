package com.crawler.app.GUI.Panels.SplashScreen;

import com.crawler.app.Utils.Res.Resources;

import java.awt.*;
import javax.swing.*;

import static com.crawler.app.Config.Bounds.*;
import static com.crawler.app.Config.Strings.*;
import static com.crawler.app.Config.Resources.*;
import static com.crawler.app.Config.Fonts.*;
import static com.crawler.app.Config.Colors.*;

public class SplashScreenPanel extends JPanel{
    private final transient Image backgroundImage;

    public SplashScreenPanel() {
        this.setSize(SPLASH_SCREEN_WIDTH, SPLASH_SCREEN_HEIGHT);
        this.setLayout(new BorderLayout());
        
        this.backgroundImage = Resources.getImage(SPLASH_SCREEN_BACKGROUND);

        JLabel labelNames = new JLabel(LABEL_OUR_NAME_STRING);
        labelNames.setFont(FONT_LABEL_WINDOW);
        labelNames.setVerticalAlignment(SwingConstants.BOTTOM);
        this.add(labelNames);
    }
    
    @Override
    protected void paintComponent(Graphics g){
        g.setColor(DEFAULT_COLOR);
        g.fillRect(DEFAULT_X, DEFAULT_Y, SPLASH_SCREEN_WIDTH, SPLASH_SCREEN_HEIGHT);
        
        g.drawImage(this.backgroundImage, DEFAULT_X, DEFAULT_Y, SPLASH_SCREEN_WIDTH, SPLASH_SCREEN_HEIGHT, null);
    }
    
}