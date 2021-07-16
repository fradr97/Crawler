package com.crawler.app.GUI.Panels;

import com.crawler.app.Logic.Crawling;
import com.crawler.app.Utils.MenuBar.SubMenuBar;
import com.crawler.app.Utils.Res.Resources;
import com.crawler.app.Utils.Components.HotArea;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

import com.crawler.app.MongoDB.MongoDBConnection;

import static com.crawler.app.Config.Bounds.*;
import static com.crawler.app.Config.Strings.*;
import static com.crawler.app.Config.Resources.*;
import static com.crawler.app.Config.Fonts.*;
import static com.crawler.app.Config.Colors.*;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
import static javax.swing.SwingConstants.CENTER;

public class SeedsPanel extends JPanel {
    private final CrawlerFrame crawlerFrame;

    private final MongoDBConnection db;

    private final transient Image backgroundImage;

    private final SubMenuBar subMenuBar;

    private final HotArea buttonBack;
    private final HotArea buttonPlay;
    private final HotArea buttonDelete;

    private final JLabel labelButtonBack;
    private final JLabel labelButtonPlay;
    private final JLabel labelButtonDelete;
    private final JLabel labelNoSeeds;

    private final JList<String> seedsList;

    public SeedsPanel(CrawlerFrame pCrawlerFrame) {
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setLayout(null);
        this.crawlerFrame = pCrawlerFrame;

        this.db = new MongoDBConnection();
        MongoDBConnection.getConnectionDB();

        this.subMenuBar = new SubMenuBar(DEFAULT_X, DEFAULT_Y, SUBMENU_BAR_DIMENSION, SUBMENU_BAR_COLOR);

        this.backgroundImage = Resources.getImage(CRAWLER_BACKGROUND);
        Image iconBack = Resources.getImage(BACK_IMAGE);
        Image iconBackHover = Resources.getImage(BACK_HOVER_IMAGE);
        Image iconPlay = Resources.getImage(PLAY_IMAGE);
        Image iconPlayHover = Resources.getImage(PLAY_HOVER_IMAGE);
        Image iconDelete = Resources.getImage(DELETE_IMAGE);
        Image iconDeleteHover = Resources.getImage(DELETE_HOVER_IMAGE);

        this.buttonBack = new HotArea(iconBack, iconBackHover, BUTTON_BACK_X,
                BUTTON_BACK_Y, BUTTONS_SUB_BAR_WIDTH, BUTTONS_SUB_BAR_HEIGHT);
        this.labelButtonBack = new JLabel();
        this.labelButtonBack.setBounds(BUTTON_BACK_X, BUTTON_BACK_Y,
                BUTTONS_SUB_BAR_WIDTH, BUTTONS_SUB_BAR_HEIGHT);
        this.labelButtonBack.addMouseListener(new MouseButtons());

        this.buttonPlay = new HotArea(iconPlay, iconPlayHover, BUTTON_LEFT_X, BUTTON_LEFT_Y,
                BUTTONS_SUB_BAR_WIDTH, BUTTONS_SUB_BAR_HEIGHT);
        this.labelButtonPlay = new JLabel();
        this.labelButtonPlay.setBounds(BUTTON_LEFT_X, BUTTON_LEFT_Y, BUTTONS_SUB_BAR_WIDTH,
                BUTTONS_SUB_BAR_HEIGHT);
        this.labelButtonPlay.addMouseListener(new MouseButtons());

        this.buttonDelete = new HotArea(iconDelete, iconDeleteHover, BUTTON_RIGHT_X,
                BUTTON_RIGHT_Y, BUTTONS_SUB_BAR_WIDTH, BUTTONS_SUB_BAR_HEIGHT);
        this.labelButtonDelete = new JLabel();
        this.labelButtonDelete.setBounds(BUTTON_RIGHT_X, BUTTON_RIGHT_Y, BUTTONS_SUB_BAR_WIDTH,
                BUTTONS_SUB_BAR_HEIGHT);
        this.labelButtonDelete.addMouseListener(new MouseButtons());

        this.labelNoSeeds = new JLabel();
        this.labelNoSeeds.setFont(FONT_LABEL);
        this.labelNoSeeds.setText(NO_SEEDS_STRING);
        int labelNoSeedsWidth = getWidthLabel(this.labelNoSeeds);
        int labelNoSeedsHeight = getHeightLabel(this.labelNoSeeds);
        this.labelNoSeeds.setBounds(centerHorizontalFrame(labelNoSeedsWidth), LABEL_NO_SEEDS_Y,
                labelNoSeedsWidth, labelNoSeedsHeight);
        this.labelNoSeeds.setVisible(false);

        this.seedsList = new JList<>(getSeedsList());
        this.seedsList.setBounds(AREA_SEEDS_X, AREA_SEEDS_Y, AREA_SEEDS_WIDTH, AREA_SEEDS_HEIGHT);
        this.seedsList.setFont(FONT_FIELD);
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) this.seedsList.getCellRenderer();
        renderer.setHorizontalAlignment(CENTER);
        this.seedsList.setFixedCellHeight(50);
        this.seedsList.repaint();

        JScrollPane scrollPane = new JScrollPane(this.seedsList);
        scrollPane.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(AREA_SEEDS_X, AREA_SEEDS_Y, AREA_SEEDS_WIDTH, AREA_SEEDS_HEIGHT);

        this.add(this.labelButtonBack);
        this.add(this.labelButtonPlay);
        this.add(this.labelButtonDelete);
        this.add(this.labelNoSeeds);
        this.add(scrollPane);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(DEFAULT_COLOR);
        g.fillRect(DEFAULT_X, DEFAULT_Y, FRAME_WIDTH, FRAME_HEIGHT);

        g.drawImage(backgroundImage, DEFAULT_X, DEFAULT_Y, FRAME_WIDTH, FRAME_HEIGHT, null);

        this.subMenuBar.drawBar(g);
        this.buttonBack.draw(g);
        this.buttonPlay.draw(g);
        this.buttonDelete.draw(g);

        this.updateSeedsList();
    }

    private DefaultListModel<String> getSeedsList() {
        DefaultListModel<String> defaultListModel = new DefaultListModel<>();
        defaultListModel.clear();

        if (this.db.checkCollectionExists()) {
            this.labelNoSeeds.setVisible(false);
            for (Crawling seed : this.db.getAllSeeds()) {
                defaultListModel.addElement(seed.seed);
            }
            return defaultListModel;
        } else {
            this.labelNoSeeds.setVisible(true);
        }
        repaint();
        return defaultListModel;
    }

    public void updateSeedsList() {
        int numberSeeds = this.seedsList.getModel().getSize();

        if (numberSeeds != db.countElementsInCollection()) {
            this.seedsList.setModel(getSeedsList());
        }
    }

    public int getItemIndex() {
        return this.seedsList.getSelectedIndex() + 1;
    }

    class WorkerDeleteCrawling extends SwingWorker<Void, Void> {

        @Override
        public Void doInBackground() {
            db.deleteCrawling(getItemIndex());
            return null;
        }

        @Override
        public void done() {
            JOptionPane.showMessageDialog(null, DELETED_SUCCESSFULLY_STRING,
                    DELETED_STRING, JOptionPane.INFORMATION_MESSAGE);
            seedsList.setModel(getSeedsList());
        }
    }

    private class MouseButtons extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel lbl = (JLabel) e.getSource();

            if (lbl.equals(labelButtonBack))
                crawlerFrame.switchPanel(crawlerFrame.getActivePanel(), crawlerFrame.crawlerPanel);

            if (lbl.equals(labelButtonPlay)) {
                if (getItemIndex() > 0) {
                    crawlerFrame.createHistoryPanel();
                    crawlerFrame.switchPanel(crawlerFrame.getActivePanel(), crawlerFrame.historyPanel);
                } else {
                    JOptionPane.showMessageDialog(null, PLEASE_SELECT_SEED_STRING,
                            ERROR_STRING, JOptionPane.ERROR_MESSAGE);
                }
            }

            if (lbl.equals(labelButtonDelete)) {
                if (getItemIndex() > 0) {
                    Object[] choices = {YES_STRING, NO_STRING, CANCEL_STRING};
                    int option = JOptionPane.showOptionDialog(null, DELETE_ONE_STRING,
                            DELETE_STRING, JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE, null, choices, JOptionPane.QUESTION_MESSAGE);

                    if (option == JOptionPane.YES_OPTION) {
                        new WorkerDeleteCrawling().execute();
                        repaint();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, PLEASE_SELECT_SEED_STRING,
                            ERROR_STRING, JOptionPane.ERROR_MESSAGE);
                }
            }

            repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            JLabel lbl = (JLabel) e.getSource();

            if (lbl.equals(labelButtonBack))
                buttonBack.setHover(true);

            if (lbl.equals(labelButtonPlay))
                buttonPlay.setHover(true);

            if (lbl.equals(labelButtonDelete))
                buttonDelete.setHover(true);

            repaint();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            JLabel lbl = (JLabel) e.getSource();

            if (lbl.equals(labelButtonBack))
                buttonBack.setHover(false);

            if (lbl.equals(labelButtonPlay))
                buttonPlay.setHover(false);

            if (lbl.equals(labelButtonDelete))
                buttonDelete.setHover(false);

            repaint();
        }
    }
}