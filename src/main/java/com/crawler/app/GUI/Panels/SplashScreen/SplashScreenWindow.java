package com.crawler.app.GUI.Panels.SplashScreen;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

import static com.crawler.app.Config.Bounds.*;
import static com.crawler.app.Config.Strings.*;

import com.mongodb.MongoTimeoutException;
import com.crawler.app.Config.Codes.*;
import com.crawler.app.GUI.Panels.WelcomePanel.WelcomeFrame;
import com.crawler.app.MongoDB.MongoDBConnection;

public class SplashScreenWindow extends JWindow {
    public final SplashScreenPanel splashScreenPanel;

    private MONGO_STATUS mongoStatus;

    public SplashScreenWindow() {
        this.setSize(SPLASH_SCREEN_WIDTH, SPLASH_SCREEN_HEIGHT);
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        this.splashScreenPanel = new SplashScreenPanel();
        this.getContentPane().add(this.splashScreenPanel);
        this.splashScreenPanel.setVisible(true);

        this.mongoStatus = MONGO_STATUS.OFF;

        new MyWorker().execute();
    }

    public class FadeThread implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(TIME_START);

                setVisible(false);
                WelcomeFrame frame = new WelcomeFrame();
                frame.setVisible(true);
            } catch (InterruptedException ex) {
                Logger.getLogger(SplashScreenWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    class MyWorker extends SwingWorker<Void, Void> {
        @Override
        public Void doInBackground() {
            try {
                MongoDBConnection db = new MongoDBConnection();
                MongoDBConnection.getConnectionDB();

                if (db.checkMongo()) {
                    mongoStatus = MONGO_STATUS.ON;
                } else {
                    mongoStatus = MONGO_STATUS.OFF;
                }
            } catch (MongoTimeoutException ex) {
                mongoStatus = MONGO_STATUS.OFF;
            }
            return null;
        }

        @Override
        public void done() {
            if (mongoStatus == MONGO_STATUS.ON) {
                Thread fade = new Thread(new FadeThread());
                fade.start();
            } else if (mongoStatus == MONGO_STATUS.OFF) {
                JOptionPane.showMessageDialog(null, MONGO_NOT_FOUND_STRING,
                        ERROR_STRING, JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        }
    }

}