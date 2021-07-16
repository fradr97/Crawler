package com.crawler.app.GUI.Panels;

import javax.swing.JFrame;
import javax.swing.JPanel;

import static com.crawler.app.Config.Bounds.*;
import static com.crawler.app.Config.Strings.*;

import com.crawler.app.Utils.MenuBar.MenuBar;

public class CrawlerFrame extends JFrame {
    public final CrawlerPanel crawlerPanel;
    public final SeedsPanel seedsPanel;
    public HistoryPanel historyPanel;

    private JPanel activePanel; /** currently active panel **/

    public CrawlerFrame() {
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setResizable(false);
        this.setLayout(null);
        this.setTitle(APPLICATION_TITLE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        MenuBar menuBar = new MenuBar(this);

        this.crawlerPanel = new CrawlerPanel(this, menuBar.itemNew, menuBar.itemExit,
                menuBar.itemDeleteOne, menuBar.itemDeleteAll, menuBar.itemHistory);
        this.getContentPane().add(this.crawlerPanel);
        this.crawlerPanel.setVisible(true);

        this.seedsPanel = new SeedsPanel(this);
        this.getContentPane().add(this.seedsPanel);
        this.seedsPanel.setVisible(false);

        this.activePanel = this.crawlerPanel;
    }

    public void createHistoryPanel() {
        this.historyPanel = new HistoryPanel(this, this.seedsPanel.getItemIndex());
        this.getContentPane().add(this.historyPanel);
    }

    public void switchPanel(JPanel toDisable, JPanel toEnable) {
        this.activePanel = toEnable;

        toDisable.setVisible(false);
        toEnable.setVisible(true);
    }

    public JPanel getActivePanel() {
        return this.activePanel;
    }
}