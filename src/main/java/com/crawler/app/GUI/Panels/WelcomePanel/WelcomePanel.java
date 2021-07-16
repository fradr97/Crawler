package com.crawler.app.GUI.Panels.WelcomePanel;

import com.crawler.app.Utils.Res.Resources;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

import com.crawler.app.GUI.Panels.CrawlerFrame;

import static com.crawler.app.Config.Bounds.*;
import static com.crawler.app.Config.Fonts.*;
import static com.crawler.app.Config.Colors.*;
import static com.crawler.app.Config.Resources.*;
import static com.crawler.app.Config.Strings.*;

public class WelcomePanel extends JPanel {
    private final WelcomeFrame welcomeFrame;

    private final transient Image backgroundImage;
    private final transient Image miniSpider;

    private final JLabel labelStart;
    private final JLabel labelExit;

    public WelcomePanel(WelcomeFrame pWelcomeFrame) {
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setLayout(null);
        this.welcomeFrame = pWelcomeFrame;

        this.backgroundImage = Resources.getImage(WELCOME_PANEL_BACKGROUND);
        this.miniSpider = Resources.getImage(MINI_SPIDER_IMAGE);

        JLabel labelTitle = new JLabel();
        labelTitle.setFont(FONT_TITLE_WELCOME);
        labelTitle.setText(TITLE_WELCOME_STRING);
        labelTitle.setForeground(DEFAULT_COLOR);
        int labelTitleWidth = getWidthLabel(labelTitle);
        int labelTitleHeight = getHeightLabel(labelTitle);
        int labelsX = centerHorizontalFrame(labelTitleWidth);

        this.labelStart = new JLabel();
        this.labelStart.setFont(FONT_LABEL_PANEL);

        this.labelExit = new JLabel();
        this.labelExit.setFont(FONT_LABEL_PANEL);

        int labelTitleY = LABEL_WELCOME_Y;
        int labelStartY = LABEL_START_WELCOME_Y;
        int labelExitY = LABEL_EXIT_WELCOME_Y;

        if (labelsX <= 0) {
            labelTitle.setFont(FONT_TITLE_WELCOME_SMALL);
            this.labelStart.setFont(FONT_LABEL_PANEL_SMALL);
            this.labelExit.setFont(FONT_LABEL_PANEL_SMALL);

            labelsX = centerHorizontalFrame(getWidthLabel(labelTitle));
            labelStartY = labelStartY - 35;
            labelExitY = labelExitY - 60;
        }

        labelTitle.setBounds(labelsX, labelTitleY, labelTitleWidth, labelTitleHeight);

        this.labelStart.setText(START_STRING);
        this.labelStart.setForeground(DEFAULT_COLOR);
        int labelStartWidth = getWidthLabel(this.labelStart);
        int labelStartHeight = getHeightLabel(this.labelStart);
        this.labelStart.setBounds(labelsX + 115, labelStartY, labelStartWidth, labelStartHeight);
        this.labelStart.addMouseListener(new MouseMainPanel());

        this.labelExit.setText(EXIT_STRING);
        this.labelExit.setForeground(DEFAULT_COLOR);
        int labelExitWidth = getWidthLabel(this.labelExit);
        int labelExitHeight = getHeightLabel(this.labelExit);
        this.labelExit.setBounds(labelsX + 125, labelExitY, labelExitWidth, labelExitHeight);
        this.labelExit.addMouseListener(new MouseMainPanel());

        this.add(labelTitle);
        this.add(this.labelStart);
        this.add(this.labelExit);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(DEFAULT_COLOR);
        g.fillRect(DEFAULT_X, DEFAULT_Y, FRAME_WIDTH, FRAME_HEIGHT);

        g.drawImage(backgroundImage, DEFAULT_X, DEFAULT_Y, FRAME_WIDTH, FRAME_HEIGHT, null);
        g.drawImage(miniSpider, MINI_SPIDER_X, DEFAULT_Y, MINI_SPIDER_WIDTH,
                MINI_SPIDER_HEIGHT, null);
    }

    public class MouseMainPanel extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel lbl = (JLabel) e.getSource();

            if (lbl.equals(labelStart)) {
                welcomeFrame.dispose();

                CrawlerFrame crawlerFrame = new CrawlerFrame();
                crawlerFrame.setVisible(true);
            }
            if (lbl.equals(labelExit)) {
                System.exit(0);
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            JLabel lbl = (JLabel) e.getSource();

            if (lbl.equals(labelStart))
                labelStart.setForeground(WELCOME_BUTTONS_HOVER_COLOR);

            if (lbl.equals(labelExit))
                labelExit.setForeground(WELCOME_BUTTONS_HOVER_COLOR);

            repaint();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            JLabel lbl = (JLabel) e.getSource();

            if (lbl.equals(labelStart))
                labelStart.setForeground(DEFAULT_COLOR);

            if (lbl.equals(labelExit))
                labelExit.setForeground(DEFAULT_COLOR);

            repaint();
        }
    }
}