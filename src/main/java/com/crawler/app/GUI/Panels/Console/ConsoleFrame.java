package com.crawler.app.GUI.Panels.Console;

import javax.swing.*;
import java.awt.*;

import static com.crawler.app.Config.Bounds.*;
import static com.crawler.app.Config.Strings.*;

public class ConsoleFrame extends JFrame {

    public ConsoleFrame(int indexSeed) {
        this.setSize(FRAME_CONSOLE_WIDTH, FRAME_CONSOLE_HEIGHT);
        this.setResizable(false);
        this.setLayout(null);
        this.setTitle(CONSOLE_STRING);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        ConsolePanel consolePanel = new ConsolePanel(this, indexSeed);
        this.getContentPane().add(consolePanel);
        consolePanel.setVisible(true);
    }

    public void closeConsole(){
        Frame[] allFrames = Frame.getFrames();

        for(Frame fr : allFrames){
            String frameName = fr.getClass().getName();
            if(frameName.equals(FRAME_CONSOLE_PATH)){
                fr.dispose();
            }
        }
    }
}
