package com.crawler.app.GUI.Panels;

import com.crawler.app.GUI.Panels.Console.ConsoleFrame;
import com.crawler.app.GUI.Panels.Tabs.FrontierTab;
import com.crawler.app.GUI.Panels.Tabs.SeedsTab;
import com.crawler.app.Utils.GenericsMethods;
import com.crawler.app.Utils.Res.Resources;
import com.crawler.app.Utils.Components.HotArea;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import static com.crawler.app.Config.Bounds.*;
import static com.crawler.app.Config.Strings.*;
import static com.crawler.app.Config.Resources.*;
import static com.crawler.app.Config.Codes.*;
import static com.crawler.app.Config.Colors.*;

import com.crawler.app.Utils.MenuBar.SubMenuBar;
import com.crawler.app.GUI.Panels.Tabs.VisitedTab;
import com.crawler.app.MongoDB.MongoDBConnection;
import com.crawler.app.Utils.Components.MyFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

public class HistoryPanel extends JPanel {

    private final MongoDBConnection db;

    private final CrawlerFrame crawlerFrame;

    private final SubMenuBar subMenuBar;

    public final FrontierTab frontierTab;
    public final VisitedTab visitedTab;
    public final SeedsTab seedsTab;

    private final HotArea buttonBack;
    private final HotArea buttonDownload;
    private final HotArea buttonConsole;

    private final transient Image backgroundImage;

    private final JLabel labelButtonDownload;
    private final JLabel labelButtonBack;
    private final JLabel labelButtonConsole;

    private final int indexSeed;

    public HistoryPanel(CrawlerFrame pCrawlerFrame, int indexSeed) {
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setLayout(null);
        this.crawlerFrame = pCrawlerFrame;
        this.indexSeed = indexSeed;

        this.db = new MongoDBConnection();
        MongoDBConnection.getConnectionDB();

        this.subMenuBar = new SubMenuBar(DEFAULT_X, DEFAULT_Y, SUBMENU_BAR_DIMENSION, SUBMENU_BAR_COLOR);

        this.backgroundImage = Resources.getImage(CRAWLER_BACKGROUND);
        Image iconBack = Resources.getImage(BACK_IMAGE);
        Image iconBackHover = Resources.getImage(BACK_HOVER_IMAGE);
        Image iconDownload = Resources.getImage(DOWNLOAD_IMAGE);
        Image iconDownloadHover = Resources.getImage(DOWNLOAD_HOVER_IMAGE);
        Image iconConsole = Resources.getImage(CONSOLE_IMAGE);
        Image iconConsoleHover = Resources.getImage(CONSOLE_HOVER_IMAGE);

        this.frontierTab = new FrontierTab(indexSeed);
        this.seedsTab = new SeedsTab(indexSeed);
        this.visitedTab = new VisitedTab(indexSeed);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(DEFAULT_X, TABBED_PANE_Y, TABBED_PANE_WIDTH, TABBED_PANE_HEIGHT);
        tabbedPane.addTab(SEED_TAB, this.seedsTab);
        tabbedPane.addTab(VISITED, this.visitedTab);
        tabbedPane.addTab(FRONTIER, this.frontierTab);

        this.buttonBack = new HotArea(iconBack, iconBackHover, BUTTON_BACK_X, BUTTON_BACK_Y,
                BUTTONS_SUB_BAR_WIDTH, BUTTONS_SUB_BAR_HEIGHT);
        this.labelButtonBack = new JLabel();
        this.labelButtonBack.setBounds(BUTTON_BACK_X, BUTTON_BACK_Y,
                BUTTONS_SUB_BAR_WIDTH, BUTTONS_SUB_BAR_HEIGHT);
        this.labelButtonBack.addMouseListener(new MouseButtonBack());

        this.buttonConsole = new HotArea(iconConsole, iconConsoleHover, BUTTON_LEFT_X,
                BUTTON_LEFT_Y, BUTTONS_SUB_BAR_WIDTH, BUTTONS_SUB_BAR_HEIGHT);
        this.labelButtonConsole = new JLabel();
        this.labelButtonConsole.setBounds(BUTTON_LEFT_X, BUTTON_LEFT_Y,
                BUTTONS_SUB_BAR_WIDTH, BUTTONS_SUB_BAR_HEIGHT);
        this.labelButtonConsole.addMouseListener(new MouseButtonConsole());

        this.buttonDownload = new HotArea(iconDownload, iconDownloadHover, BUTTON_RIGHT_X, BUTTON_RIGHT_Y,
                BUTTONS_SUB_BAR_WIDTH, BUTTONS_SUB_BAR_HEIGHT);
        this.labelButtonDownload = new JLabel();
        this.labelButtonDownload.setBounds(BUTTON_RIGHT_X, BUTTON_RIGHT_Y,
                BUTTONS_SUB_BAR_WIDTH, BUTTONS_SUB_BAR_HEIGHT);
        this.labelButtonDownload.addMouseListener(new MouseButtonDownload());

        this.add(tabbedPane);
        this.add(this.labelButtonBack);
        this.add(this.labelButtonDownload);
        this.add(this.labelButtonConsole);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(DEFAULT_COLOR);
        g.fillRect(DEFAULT_X, DEFAULT_Y, FRAME_WIDTH, FRAME_HEIGHT);

        g.drawImage(backgroundImage, DEFAULT_X, DEFAULT_Y, FRAME_WIDTH, FRAME_HEIGHT, null);

        this.subMenuBar.drawBar(g);
        this.buttonBack.draw(g);
        this.buttonDownload.draw(g);
        this.buttonConsole.draw(g);
    }

    public List<String> getFrontier(int index) {
        if (this.db.checkCollectionExists()) {
            return this.db.getUrls(CRAWL_FRONTIER, index);
        }
        return null;
    }

    public class MouseButtonBack extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel lbl = (JLabel) e.getSource();

            if (lbl.equals(labelButtonBack)) {
                crawlerFrame.switchPanel(crawlerFrame.getActivePanel(), crawlerFrame.seedsPanel);
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            buttonBack.setHover(true);
            repaint();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            buttonBack.setHover(false);
            repaint();
        }
    }

    public class MouseButtonDownload extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel lbl = (JLabel) e.getSource();

            if (lbl.equals(labelButtonDownload)) {
                new MyWorker().execute();
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            buttonDownload.setHover(true);
            repaint();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            buttonDownload.setHover(false);
            repaint();
        }
    }

    public class MouseButtonConsole extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel lbl = (JLabel) e.getSource();

            if (lbl.equals(labelButtonConsole)) {
                ConsoleFrame consoleFrame = new ConsoleFrame(indexSeed);
                consoleFrame.setVisible(true);
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            buttonConsole.setHover(true);
            repaint();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            buttonConsole.setHover(false);
            repaint();
        }
    }

    class MyWorker extends SwingWorker<Void, Void> {

        @Override
        public Void doInBackground() {
            String userHomeFolder;

            if (Objects.equals(GenericsMethods.getOS(), WINDOWS)) {
                userHomeFolder = System.getProperty(HOME_DIRECTORY)
                        .concat(BACK_SLASH)
                        .concat(NAME_FILE);
            } else {
                userHomeFolder = System.getProperty(HOME_DIRECTORY)
                        .concat(SLASH)
                        .concat(NAME_FILE);
            }

            String answer = JOptionPane.showInputDialog(null, INSERT_PATH_STRING
                    + userHomeFolder + RIGHT_BRACKET, SAVE_STRING, JOptionPane.INFORMATION_MESSAGE);

            try {
                MyFile file = new MyFile();

                switch (file.createFile(answer)) {
                    case OK:
                        file.writeUrlFile(getFrontier(indexSeed));
                        JOptionPane.showMessageDialog(null, FILE_DONE_STRING,
                                MESSAGE_STRING, JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case EMPTY:
                        JOptionPane.showMessageDialog(null, EMPTY_PATH_FILE_STRING,
                                ERROR_STRING, JOptionPane.ERROR_MESSAGE);
                        break;
                    case NONE:
                        JOptionPane.showMessageDialog(null, ERROR_FILE_STRING,
                                ERROR_STRING, JOptionPane.ERROR_MESSAGE);
                        break;
                    case EXISTS:
                        JOptionPane.showMessageDialog(null, EXISTS_FILE_STRING,
                                ERROR_STRING, JOptionPane.ERROR_MESSAGE);
                        break;
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ERROR_FILE_STRING, ERROR_STRING,
                        JOptionPane.ERROR_MESSAGE);
            }
            return null;
        }
    }
}
