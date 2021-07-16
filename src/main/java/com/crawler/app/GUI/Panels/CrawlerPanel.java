package com.crawler.app.GUI.Panels;

import com.crawler.app.MongoDB.MongoDBConnection;
import com.crawler.app.Logic.Crawler;
import com.crawler.app.Utils.Res.Resources;
import com.crawler.app.Utils.Components.HotArea;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import static com.crawler.app.Config.Bounds.*;
import static com.crawler.app.Config.Strings.*;
import static com.crawler.app.Config.Resources.*;
import static com.crawler.app.Config.Fonts.*;
import static com.crawler.app.Config.Colors.*;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
import static javax.swing.SwingConstants.HORIZONTAL;

import com.crawler.app.GUI.Panels.WelcomePanel.WelcomeFrame;
import com.crawler.app.Utils.MenuBar.SubMenuBar;

import javax.swing.ImageIcon;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

public class CrawlerPanel extends JPanel {

    private final MongoDBConnection db;
    private transient Crawler crawler;

    private final CrawlerFrame crawlerFrame;

    private final JMenuItem itemNew;
    private final JMenuItem itemExit;
    private final JMenuItem itemDeleteOne;
    private final JMenuItem itemDeleteAll;
    private final JMenuItem itemHistory;

    private final SubMenuBar subMenuBar;

    private final JTextField fieldUrl;
    private final JTextField fieldWeight;

    private final JTextArea areaCrawling;
    private final JProgressBar progressBar;

    private final HotArea buttonBack;
    private final HotArea buttonPlay;
    private final HotArea buttonStop;

    private final transient Image backgroundImage;

    private final JLabel labelButtonBack;
    private final JLabel labelButtonPlay;
    private final JLabel labelButtonStop;

    /**
     * disable buttons during Crawling
     **/
    private boolean active = true;

    public CrawlerPanel(CrawlerFrame pCrawlerFrame, JMenuItem pItem1, JMenuItem pItem2,
                        JMenuItem pItem3, JMenuItem pItem4, JMenuItem pItem5) {
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setLayout(null);
        this.crawlerFrame = pCrawlerFrame;

        this.itemNew = pItem1;
        this.itemExit = pItem2;
        this.itemDeleteOne = pItem3;
        this.itemDeleteAll = pItem4;
        this.itemHistory = pItem5;

        this.itemNew.setIcon(new ImageIcon(Resources.getImage(NEW_ICON)));
        this.itemExit.setIcon(new ImageIcon(Resources.getImage(EXIT_ICON)));
        this.itemDeleteOne.setIcon(new ImageIcon(Resources.getImage(DELETE_ONE_ICON)));
        this.itemDeleteAll.setIcon(new ImageIcon(Resources.getImage(DELETE_ALL_ICON)));
        this.itemHistory.setIcon(new ImageIcon(Resources.getImage(HISTORY_ICON)));

        this.itemNew.addMouseListener(new MouseMenuItems());
        this.itemExit.addMouseListener(new MouseMenuItems());
        this.itemDeleteOne.addMouseListener(new MouseMenuItems());
        this.itemDeleteAll.addMouseListener(new MouseMenuItems());
        this.itemHistory.addMouseListener(new MouseMenuItems());

        this.subMenuBar = new SubMenuBar(DEFAULT_X, DEFAULT_Y, SUBMENU_BAR_DIMENSION, SUBMENU_BAR_COLOR);

        this.db = new MongoDBConnection();
        MongoDBConnection.getConnectionDB();

        this.backgroundImage = Resources.getImage(CRAWLER_BACKGROUND);
        Image iconPlay = Resources.getImage(PLAY_IMAGE);
        Image iconPlayHover = Resources.getImage(PLAY_HOVER_IMAGE);
        Image iconStop = Resources.getImage(STOP_IMAGE);
        Image iconStopHover = Resources.getImage(STOP_HOVER_IMAGE);
        Image iconBack = Resources.getImage(BACK_IMAGE);
        Image iconBackHover = Resources.getImage(BACK_HOVER_IMAGE);

        JLabel labelUrl = new JLabel();
        labelUrl.setFont(FONT_LABEL);
        labelUrl.setText(INSERT_SEED_URL_STRING);
        int labelUrlWidth = getWidthLabel(labelUrl);
        int labelUrlHeight = getHeightLabel(labelUrl);
        labelUrl.setBounds(LABEL_URL_X, LABELS_CRAWLING_Y, labelUrlWidth, labelUrlHeight);
        labelUrl.setForeground(DEFAULT_COLOR);

        JLabel labelWeight = new JLabel();
        labelWeight.setFont(FONT_LABEL);
        labelWeight.setText(INSERT_WEIGHT_STRING);
        int labelWeightWidth = getWidthLabel(labelWeight);
        int labelWeightHeight = getHeightLabel(labelWeight);
        labelWeight.setBounds(LABEL_WEIGHT_X, LABELS_CRAWLING_Y, labelWeightWidth, labelWeightHeight);
        labelWeight.setForeground(DEFAULT_COLOR);

        this.fieldUrl = new JTextField();
        this.fieldUrl.setBounds(FIELD_URL_X, FIELD_URL_Y, LARGE_FIELD_WIDTH, LARGE_FIELD_HEIGHT);
        this.fieldUrl.setFont(FONT_FIELD);

        this.fieldWeight = new JTextField();
        this.fieldWeight.setBounds(FIELD_WEIGHT_X, FIELD_WEIGHT_Y, LARGE_FIELD_WIDTH, LARGE_FIELD_HEIGHT);
        this.fieldWeight.setFont(FONT_FIELD);

        this.buttonBack = new HotArea(iconBack, iconBackHover, BUTTON_BACK_X, BUTTON_BACK_Y,
                BUTTONS_SUB_BAR_WIDTH, BUTTONS_SUB_BAR_HEIGHT);
        this.labelButtonBack = new JLabel();
        this.labelButtonBack.setBounds(BUTTON_BACK_X, BUTTON_BACK_Y, BUTTONS_SUB_BAR_WIDTH,
                BUTTONS_SUB_BAR_HEIGHT);
        this.labelButtonBack.addMouseListener(new MouseButtonsPanel());

        this.buttonPlay = new HotArea(iconPlay, iconPlayHover, BUTTON_LEFT_X, BUTTON_LEFT_Y,
                BUTTONS_SUB_BAR_WIDTH, BUTTONS_SUB_BAR_HEIGHT);
        this.labelButtonPlay = new JLabel();
        this.labelButtonPlay.setBounds(BUTTON_LEFT_X, BUTTON_LEFT_Y, BUTTONS_SUB_BAR_WIDTH,
                BUTTONS_SUB_BAR_HEIGHT);
        this.labelButtonPlay.addMouseListener(new MouseButtonsPanel());

        this.buttonStop = new HotArea(iconStop, iconStopHover, BUTTON_RIGHT_X, BUTTON_RIGHT_Y,
                BUTTONS_SUB_BAR_HEIGHT, BUTTONS_SUB_BAR_HEIGHT);
        this.labelButtonStop = new JLabel();
        this.labelButtonStop.setBounds(BUTTON_RIGHT_X, BUTTON_RIGHT_Y, BUTTONS_SUB_BAR_HEIGHT,
                BUTTONS_SUB_BAR_HEIGHT);
        this.labelButtonStop.addMouseListener(new MouseButtonsPanel());

        JTextArea areaCrawlingAttributes = new JTextArea();
        areaCrawlingAttributes.setFont(FONT_AREA);
        areaCrawlingAttributes.setText(CRAWLING_ATTRIBUTES);
        areaCrawlingAttributes.setBounds(AREAS_CENTERED, AREA_CRAWLING_Y, AREA_ATTRIBUTES_WIDTH,
                AREA_CRAWLING_HEIGHT);
        areaCrawlingAttributes.setEditable(false);

        this.areaCrawling = new JTextArea();
        this.areaCrawling.setFont(FONT_AREA);
        this.areaCrawling.setText(START_CRAWLING_STRING);
        this.areaCrawling.setBounds(AREA_CRAWLING_X, AREA_CRAWLING_Y, AREA_CRAWLING_WIDTH,
                AREA_CRAWLING_HEIGHT);
        this.areaCrawling.setEditable(false);

        JScrollPane jScrollPane = new JScrollPane(this.areaCrawling);
        jScrollPane.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setBounds(AREA_CRAWLING_X, AREA_CRAWLING_Y, AREA_CRAWLING_WIDTH, AREA_CRAWLING_HEIGHT);

        this.progressBar = new JProgressBar(HORIZONTAL);
        this.progressBar.setBounds(PROGRESS_BAR_X, PROGRESS_BAR_Y, PROGRESS_BAR_WIDTH, PROGRESS_BAR_HEIGHT);
        this.progressBar.setStringPainted(true);
        this.progressBar.setBackground(DEFAULT_COLOR);
        this.progressBar.setForeground(PROGRESS_BAR_BACKGROUND_COLOR);
        this.progressBar.setFont(FONT_FIELD);
        this.progressBar.setString(IN_PROGRESS_STRING);
        this.progressBar.setIndeterminate(true);
        this.progressBar.setVisible(false);

        this.add(labelUrl);
        this.add(labelWeight);
        this.add(this.fieldUrl);
        this.add(this.fieldWeight);
        this.add(this.labelButtonBack);
        this.add(this.labelButtonPlay);
        this.add(this.labelButtonStop);
        this.add(areaCrawlingAttributes);
        this.add(jScrollPane);
        this.add(this.progressBar);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(DEFAULT_COLOR);
        g.fillRect(DEFAULT_X, DEFAULT_Y, FRAME_WIDTH, FRAME_HEIGHT);

        g.drawImage(backgroundImage, DEFAULT_X, DEFAULT_Y, FRAME_WIDTH, FRAME_HEIGHT, null);

        this.subMenuBar.drawBar(g);
        this.buttonPlay.draw(g);
        this.buttonStop.draw(g);
        this.buttonBack.draw(g);
    }

    private String getUrl() {
        return this.fieldUrl.getText();
    }

    private int getWeight() {
        return Integer.parseInt(this.fieldWeight.getText());
    }

    private boolean checkUrl() {
        return this.getUrl() != null && !this.getUrl().equals(EMPTY_STRING) && isValidURL(this.getUrl());
    }

    private boolean checkWeight() {
        try{
            return this.getWeight() > 0;
        }catch (Exception e){
            return false;
        }
    }

    public boolean isValidURL(String url) {
        try {
            new URL(url).toURI();
        } catch (MalformedURLException | URISyntaxException e) {
            return false;
        }

        return true;
    }

    private void deleteAll() {
        refresh();
        this.db.dropDatabase();
    }

    private void refresh() {
        this.fieldUrl.setText(EMPTY_STRING);
        this.fieldWeight.setText(EMPTY_STRING);
        this.areaCrawling.setText(START_CRAWLING_STRING);
        repaint();
        javax.swing.MenuSelectionManager.defaultManager().clearSelectedPath();
    }

    public class MouseButtonsPanel extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel lbl = (JLabel) e.getSource();

            if (active) {
                if (lbl.equals(labelButtonPlay)) {
                    try {
                        if (checkUrl() && checkWeight()) {
                            new MyWorker().execute();
                            repaint();
                        } else {
                            JOptionPane.showMessageDialog(null, INVALID_URL_WEIGHT_STRING,
                                    ERROR_STRING, JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (HeadlessException ex) {
                        JOptionPane.showMessageDialog(null, INVALID_URL_WEIGHT_STRING,
                                ERROR_STRING, JOptionPane.ERROR_MESSAGE);
                    }
                }
                if (lbl.equals(labelButtonBack)) {
                    refresh();
                    crawlerFrame.dispose();
                    WelcomeFrame welcomeFrame = new WelcomeFrame();
                    welcomeFrame.setVisible(true);
                }
            } else {
                if (lbl.equals(labelButtonStop)) {
                    crawler.stopCrawling();
                }
            }
            repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            JLabel lbl = (JLabel) e.getSource();

            if (lbl.equals(labelButtonStop))
                buttonStop.setHover(true);

            if (active) {
                if (lbl.equals(labelButtonPlay))
                    buttonPlay.setHover(true);

                if (lbl.equals(labelButtonBack))
                    buttonBack.setHover(true);
            }

            repaint();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            JLabel lbl = (JLabel) e.getSource();

            if (lbl.equals(labelButtonStop))
                buttonStop.setHover(false);

            if (active) {
                if (lbl.equals(labelButtonPlay))
                    buttonPlay.setHover(false);

                if (lbl.equals(labelButtonBack))
                    buttonBack.setHover(false);
            }

            repaint();
        }
    }

    public class MouseMenuItems extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            JMenuItem item = (JMenuItem) e.getSource();

            if (active) {
                if (item.equals(itemNew)) {
                    crawlerFrame.switchPanel(crawlerFrame.getActivePanel(), crawlerFrame.crawlerPanel);
                    refresh();
                }
                if (item.equals(itemExit)) {
                    System.exit(0);
                }
                if (item.equals(itemDeleteOne)) {
                    crawlerFrame.switchPanel(crawlerFrame.getActivePanel(), crawlerFrame.seedsPanel);
                    refresh();
                }
                if (item.equals(itemDeleteAll)) {
                    Object[] choices = {YES_STRING, NO_STRING, CANCEL_STRING};
                    int option = JOptionPane.showOptionDialog(null, DELETE_EVERYTHING_STRING,
                            DELETE_STRING, JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE, null, choices, JOptionPane.QUESTION_MESSAGE);

                    if (option == JOptionPane.YES_OPTION) {
                        deleteAll();
                        try {
                            crawlerFrame.historyPanel.seedsTab.resetSeeds();
                            crawlerFrame.historyPanel.visitedTab.resetSeeds();
                            crawlerFrame.historyPanel.frontierTab.resetSeeds();
                            repaint();
                        } catch (Exception ex) {
                        }
                    }
                }
                if (item.equals(itemHistory)) {
                    crawlerFrame.switchPanel(crawlerFrame.getActivePanel(), crawlerFrame.seedsPanel);
                    refresh();
                }
            }
        }
    }

    class MyWorker extends SwingWorker<Void, Void> {

        @Override
        public Void doInBackground() {
            active = false;

            fieldUrl.setEditable(false);
            fieldWeight.setEditable(false);

            progressBar.setVisible(true);
            progressBar.setIndeterminate(true);
            progressBar.repaint();
            crawler = new Crawler();
            crawler.setCrawling(getUrl(), getWeight(), areaCrawling);
            repaint();
            return null;
        }

        @Override
        public void done() {
            progressBar.setVisible(false);
            active = true;

            fieldUrl.setText(EMPTY_STRING);
            fieldWeight.setText(EMPTY_STRING);

            fieldUrl.setEditable(true);
            fieldWeight.setEditable(true);
            repaint();
        }
    }
}
