package com.crawler.app.GUI.Panels.Console;

import com.crawler.app.MongoDB.MongoDBConnection;
import com.crawler.app.Utils.GenericsMethods;
import com.crawler.app.Utils.Res.Resources;
import com.crawler.app.Utils.Components.HotArea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import static com.crawler.app.Config.Bounds.*;
import static com.crawler.app.Config.Strings.*;
import static com.crawler.app.Config.Resources.*;
import static com.crawler.app.Config.Fonts.*;
import static com.crawler.app.Config.Codes.*;
import static com.crawler.app.Config.Colors.*;

public class ConsolePanel extends JPanel {
    private final MongoDBConnection db;

    private final ConsoleFrame consoleFrame;

    private final HotArea buttonEdge;
    private final HotArea buttonFirefox;
    private final HotArea buttonChrome;
    private final HotArea buttonRefresh;

    private final JLabel labelButtonEdge;
    private final JLabel labelButtonFirefox;
    private final JLabel labelButtonChrome;
    private final JLabel labelButtonRefresh;

    private final JTextField fieldExecCommand;
    private final JTextField fieldRangeFrom;
    private final JTextField fieldRangeTo;

    private final ButtonGroup group;

    private final JRadioButton visitedRadio;
    private final JRadioButton frontierRadio;

    private final JButton buttonLinks;
    private final JButton buttonConfirm;
    private final JButton buttonClose;

    private final int indexSeed;
    private int indexNextUrl;
    private boolean executeAllLinks;
    private boolean enableButtons;

    public ConsolePanel(ConsoleFrame pConsoleFrame, int indexSeed) {
        this.setSize(FRAME_CONSOLE_WIDTH, FRAME_CONSOLE_HEIGHT);
        this.setLayout(null);
        this.consoleFrame = pConsoleFrame;
        this.indexSeed = indexSeed;
        this.indexNextUrl = 0;
        this.executeAllLinks = false;
        this.enableButtons = true;

        this.db = new MongoDBConnection();
        MongoDBConnection.getConnectionDB();

        MouseConsole mouseConsole = new MouseConsole();

        Image iconEdge = Resources.getImage(EDGE_ICON);
        Image iconEdgeHover = Resources.getImage(EDGE_HOVER_ICON);
        Image iconFirefox = Resources.getImage(FIREFOX_ICON);
        Image iconFirefoxHover = Resources.getImage(FIREFOX_HOVER_ICON);
        Image iconChrome = Resources.getImage(CHROME_ICON);
        Image iconChromeHover = Resources.getImage(CHROME_HOVER_ICON);
        Image iconRefresh = Resources.getImage(REFRESH_ICON);
        Image iconRefreshHover = Resources.getImage(REFRESH_HOVER_ICON);

        JLabel labelExec = new JLabel(ENTER_COMMAND_EXEC_STRING);
        labelExec.setFont(FONT_LABEL_CONSOLE);
        int labelExecWidth = getWidthLabel(labelExec);
        int labelExecHeight = getHeightLabel(labelExec);
        labelExec.setBounds(LABELS_CONSOLE_X, LABEL_COMMAND_EXEC_Y, labelExecWidth, labelExecHeight);
        labelExec.setForeground(LABEL_CONSOLE_COLOR);

        int RANGE_COLL_STRING_Y = BUTTON_LINKS_CONSOLE_Y + BUTTON_LINKS_CONSOLE_HEIGHT + 15;
        JLabel labelRangeCollections = new JLabel(ENTER_RANGE_ID_STRING);
        labelRangeCollections.setFont(FONT_LABEL_CONSOLE);
        int labelRangeCollWidth = getWidthLabel(labelRangeCollections);
        int labelRangeCollHeight = getHeightLabel(labelRangeCollections);
        labelRangeCollections.setBounds(LABELS_CONSOLE_X, RANGE_COLL_STRING_Y,
                labelRangeCollWidth, labelRangeCollHeight);
        labelRangeCollections.setForeground(LABEL_CONSOLE_COLOR);

        this.buttonEdge = new HotArea(iconEdge, iconEdgeHover, BUTTON_EDGE_CONSOLE_X,
                BUTTONS_BROWSER_Y, BUTTONS_BROWSER_WIDTH, BUTTONS_BROWSER_HEIGHT);
        this.labelButtonEdge = new JLabel();
        this.labelButtonEdge.setBounds(BUTTON_EDGE_CONSOLE_X, BUTTONS_BROWSER_Y,
                BUTTONS_BROWSER_WIDTH, BUTTONS_BROWSER_HEIGHT);
        this.labelButtonEdge.addMouseListener(new MouseButtonsBrowser());

        this.buttonFirefox = new HotArea(iconFirefox, iconFirefoxHover,
                BUTTON_FIREFOX_CONSOLE_X, BUTTONS_BROWSER_Y, BUTTONS_BROWSER_WIDTH, BUTTONS_BROWSER_HEIGHT);
        this.labelButtonFirefox = new JLabel();
        this.labelButtonFirefox.setBounds(BUTTON_FIREFOX_CONSOLE_X, BUTTONS_BROWSER_Y,
                BUTTONS_BROWSER_WIDTH, BUTTONS_BROWSER_HEIGHT);
        this.labelButtonFirefox.addMouseListener(new MouseButtonsBrowser());

        this.buttonChrome = new HotArea(iconChrome, iconChromeHover,
                BUTTON_CHROME_CONSOLE_X, BUTTONS_BROWSER_Y, BUTTONS_BROWSER_WIDTH, BUTTONS_BROWSER_HEIGHT);
        this.labelButtonChrome = new JLabel();
        this.labelButtonChrome.setBounds(BUTTON_CHROME_CONSOLE_X, BUTTONS_BROWSER_Y,
                BUTTONS_BROWSER_WIDTH, BUTTONS_BROWSER_HEIGHT);
        this.labelButtonChrome.addMouseListener(new MouseButtonsBrowser());

        this.buttonRefresh = new HotArea(iconRefresh, iconRefreshHover,
                BUTTON_REFRESH_CONSOLE_X, BUTTON_REFRESH_CONSOLE_Y, BUTTON_REFRESH_DIMENSION, BUTTON_REFRESH_DIMENSION);
        this.labelButtonRefresh = new JLabel();
        this.labelButtonRefresh.setBounds(BUTTON_REFRESH_CONSOLE_X, BUTTON_REFRESH_CONSOLE_Y,
                BUTTON_REFRESH_DIMENSION, BUTTON_REFRESH_DIMENSION);
        this.labelButtonRefresh.addMouseListener(new MouseButtonsBrowser());

        this.fieldExecCommand = new JTextField();
        this.fieldExecCommand.setBounds(FIELDS_CONSOLE_X, FIELD_EXEC_COMMAND_Y,
                LARGE_FIELD_WIDTH, LARGE_FIELD_HEIGHT);
        this.fieldExecCommand.setFont(FONT_FIELD);

        int FIELD_RANGE_Y = RANGE_COLL_STRING_Y + 35;
        this.fieldRangeFrom = new JTextField();
        this.fieldRangeFrom.setBounds(FIELDS_CONSOLE_X, FIELD_RANGE_Y,
                SMALL_FIELD_WITH, LARGE_FIELD_HEIGHT);
        this.fieldRangeFrom.setFont(FONT_FIELD);

        this.fieldRangeTo = new JTextField();
        this.fieldRangeTo.setBounds(FIELD_TO_CONSOLE_X, FIELD_RANGE_Y,
                SMALL_FIELD_WITH, LARGE_FIELD_HEIGHT);
        this.fieldRangeTo.setFont(FONT_FIELD);

        int RADIO_Y = FIELD_RANGE_Y + 10;
        this.visitedRadio = new JRadioButton(VISITED.toUpperCase());
        this.visitedRadio.setBounds(RADIO_BUTTONS_CONSOLE_X, RADIO_Y,
                RADIO_BUTTON_WIDTH, RADIO_BUTTON_HEIGHT);
        this.visitedRadio.setActionCommand(VISITED);
        this.visitedRadio.setBackground(DEFAULT_COLOR);
        this.visitedRadio.setSelected(true);

        this.frontierRadio = new JRadioButton(FRONTIER.toUpperCase());
        this.frontierRadio.setBounds(RADIO_BUTTONS_CONSOLE_X, RADIO_Y + RADIO_BUTTON_HEIGHT,
                RADIO_BUTTON_WIDTH, RADIO_BUTTON_HEIGHT);
        this.frontierRadio.setActionCommand(CRAWL_FRONTIER);
        this.frontierRadio.setBackground(DEFAULT_COLOR);
        this.frontierRadio.setSelected(false);

        this.group = new ButtonGroup();
        this.group.add(this.visitedRadio);
        this.group.add(this.frontierRadio);

        this.buttonLinks = new JButton();
        this.buttonLinks.setText(RUN_INDIVIDUAL_LINK_STRING);
        this.buttonLinks.setBackground(BUTTON_LINK_COLOR);
        this.buttonLinks.setForeground(DEFAULT_COLOR);
        this.buttonLinks.setBounds(BUTTON_LINKS_CONSOLE_X, BUTTON_LINKS_CONSOLE_Y,
                BUTTON_LINKS_CONSOLE_WIDTH, BUTTON_LINKS_CONSOLE_HEIGHT);
        this.buttonLinks.addMouseListener(mouseConsole);

        this.buttonConfirm = new JButton();
        this.buttonConfirm.setText(CONFIRM_STRING);
        this.buttonConfirm.setBounds(BUTTON_CONFIRM_CONSOLE_X, BUTTONS_CONSOLE_Y,
                BUTTONS_CONSOLE_WIDTH, BUTTONS_CONSOLE_HEIGHT);
        this.buttonConfirm.addMouseListener(mouseConsole);

        this.buttonClose = new JButton();
        this.buttonClose.setText(CLOSE_STRING);
        this.buttonClose.setBounds(BUTTON_CLOSE_CONSOLE_X, BUTTONS_CONSOLE_Y,
                BUTTONS_CONSOLE_WIDTH, BUTTONS_CONSOLE_HEIGHT);
        this.buttonClose.addMouseListener(mouseConsole);

        this.add(labelExec);
        this.add(labelRangeCollections);
        this.add(this.labelButtonEdge);
        this.add(this.labelButtonFirefox);
        this.add(this.labelButtonChrome);
        this.add(this.labelButtonRefresh);
        this.add(this.fieldExecCommand);
        this.add(this.fieldRangeFrom);
        this.add(this.fieldRangeTo);
        this.add(this.visitedRadio);
        this.add(this.frontierRadio);
        this.add(this.buttonLinks);
        this.add(this.buttonConfirm);
        this.add(this.buttonClose);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(DEFAULT_COLOR);
        g.fillRect(DEFAULT_X, DEFAULT_Y, FRAME_CONSOLE_WIDTH, FRAME_CONSOLE_HEIGHT);

        this.buttonEdge.draw(g);
        this.buttonFirefox.draw(g);
        this.buttonChrome.draw(g);
        this.buttonRefresh.draw(g);
    }

    private void setCommandConsole(String command) {
        this.fieldExecCommand.setText(command);
    }

    private String getCommandConsole() {
        return this.fieldExecCommand.getText();
    }

    private int getFromConsole() {
        if (!this.fieldRangeFrom.getText().equals(EMPTY_STRING))
            return Integer.parseInt(this.fieldRangeFrom.getText());

        return 0;
    }

    private int getToConsole() {
        if (!this.fieldRangeTo.getText().equals(EMPTY_STRING))
            return Integer.parseInt(this.fieldRangeTo.getText());

        return 0;
    }

    public void execCommand(String genericCommand, String url) {
        try {
            String[] command = {genericCommand, url};

            if (url.equals(EMPTY_STRING))
                Runtime.getRuntime().exec(genericCommand);
            else
                Runtime.getRuntime().exec(command);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ERROR_CONSOLE_STRING, ERROR_STRING,
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private int checkCommandToExecute() {
        if (!getCommandConsole().equals(EMPTY_STRING) && getFromConsole() == 0 || getToConsole() == 0) {
            return EXEC_ONLY_COMMAND;
        }
        if (getCommandConsole().equals(EMPTY_STRING) ||
                (getFromConsole() < 1 && getFromConsole() != 0) ||
                (getToConsole() < 1 && getToConsole() != 0)
                || getFromConsole() > getToConsole()) {
            return ERROR_CONSOLE;
        }
        if (!getCommandConsole().equals(EMPTY_STRING) &&
                getFromConsole() > 0 && getToConsole() > 0 &&
                getFromConsole() <= getToConsole() &&
                !this.executeAllLinks) {
            return EXEC_COMMAND_AND_RANGE_SINGLE;
        }
        if (!getCommandConsole().equals(EMPTY_STRING) &&
                getFromConsole() > 0 && getToConsole() > 0 &&
                getFromConsole() <= getToConsole() &&
                this.executeAllLinks) {
            return EXEC_COMMAND_AND_RANGE_ALL;
        }

        return ERROR_CONSOLE;
    }

    private void enableConsole(boolean enable) {
        this.fieldExecCommand.setEditable(enable);
        this.fieldRangeFrom.setEditable(enable);
        this.fieldRangeTo.setEditable(enable);
        this.visitedRadio.setEnabled(enable);
        this.frontierRadio.setEnabled(enable);
        this.buttonLinks.setEnabled(enable);
        this.enableButtons = enable;
    }

    private void refreshConsole() {
        this.setCommandConsole(EMPTY_STRING);
        this.fieldRangeFrom.setText(EMPTY_STRING);
        this.fieldRangeTo.setText(EMPTY_STRING);
        this.buttonConfirm.setText(CONFIRM_STRING);
        this.visitedRadio.setSelected(true);
        this.frontierRadio.setSelected(false);
        this.buttonLinks.setBackground(BUTTON_LINK_COLOR);
        this.buttonLinks.setText(RUN_INDIVIDUAL_LINK_STRING);
        this.enableConsole(true);
        this.indexNextUrl = 0;
        this.executeAllLinks = false;
    }

    private void getBrowser(String browser) {
        String os = GenericsMethods.getOS();

        switch (os) {
            case WINDOWS:
                if (EDGE.equals(browser)) {
                    setCommandConsole(System.getenv(PROGRAM_FILES_STRING)
                            .concat(EDGE_PATH_WINDOWS));
                } else if (FIREFOX.equals(browser)) {
                    setCommandConsole(System.getenv(PROGRAM_FILES_STRING)
                            .concat(FIREFOX_PATH_WINDOWS));
                } else if (CHROME.equals(browser)) {
                    setCommandConsole(System.getenv(PROGRAM_FILES_STRING)
                            .concat(CHROME_PATH_WINDOWS));
                }
                break;
            case LINUX:
                if (EDGE.equals(browser)) {
                    setCommandConsole(GenericsMethods.getBrowserPath(EDGE));
                } else if (FIREFOX.equals(browser)) {
                    setCommandConsole(GenericsMethods.getBrowserPath(FIREFOX));
                } else if (CHROME.equals(browser)) {
                    setCommandConsole(GenericsMethods.getBrowserPath(CHROME));
                }
                break;
            case OTHER_OS:
                JOptionPane.showMessageDialog(null, INSERT_BROWSER_STRING, ERROR_STRING,
                        JOptionPane.ERROR_MESSAGE);
                break;
        }
    }

    public class MouseButtonsBrowser extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel lbl = (JLabel) e.getSource();

            if (enableButtons) {
                if (lbl.equals(labelButtonEdge))
                    getBrowser(EDGE);

                if (lbl.equals(labelButtonFirefox))
                    getBrowser(FIREFOX);

                if (lbl.equals(labelButtonChrome))
                    getBrowser(CHROME);
            }

            if (lbl.equals(labelButtonRefresh))
                refreshConsole();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            JLabel lbl = (JLabel) e.getSource();

            if (enableButtons) {
                if (lbl.equals(labelButtonEdge))
                    buttonEdge.setHover(true);

                if (lbl.equals(labelButtonFirefox))
                    buttonFirefox.setHover(true);

                if (lbl.equals(labelButtonChrome))
                    buttonChrome.setHover(true);
            }

            if (lbl.equals(labelButtonRefresh))
                buttonRefresh.setHover(true);

            repaint();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            JLabel lbl = (JLabel) e.getSource();

            if (enableButtons) {
                if (lbl.equals(labelButtonEdge))
                    buttonEdge.setHover(false);

                if (lbl.equals(labelButtonChrome))
                    buttonChrome.setHover(false);

                if (lbl.equals(labelButtonFirefox))
                    buttonFirefox.setHover(false);
            }

            if (lbl.equals(labelButtonRefresh))
                buttonRefresh.setHover(false);

            repaint();
        }
    }

    public class MouseConsole extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            JButton btn = (JButton) e.getSource();

            if (btn.equals(buttonLinks) && enableButtons) {
                if (!executeAllLinks) {
                    executeAllLinks = true;
                    buttonLinks.setText(RUN_ALL_LINKS_STRING);
                    buttonLinks.setBackground(BUTTON_LINK_HOVER_COLOR);
                } else {
                    executeAllLinks = false;
                    buttonLinks.setText(RUN_INDIVIDUAL_LINK_STRING);
                    buttonLinks.setBackground(BUTTON_LINK_COLOR);
                }
                repaint();
            }

            if (btn.equals(buttonConfirm)) {
                try {
                    String urlSet = group.getSelection().getActionCommand();
                    String command = getCommandConsole();
                    int pFrom = getFromConsole();
                    int pTo = getToConsole();
                    int check = checkCommandToExecute();

                    switch (check) {
                        case EXEC_ONLY_COMMAND:
                            execCommand(command, EMPTY_STRING);
                            break;
                        case EXEC_COMMAND_AND_RANGE_SINGLE:
                            if (indexNextUrl < db.getRangeUrls(urlSet, indexSeed, pFrom, pTo).size()) {
                                execCommand(command, db.getRangeUrls(urlSet, indexSeed, pFrom, pTo)
                                        .get(indexNextUrl));
                                buttonConfirm.setText(NEXT_URL_STRING);
                                enableConsole(false);
                                indexNextUrl++;
                            } else {
                                refreshConsole();
                            }
                            repaint();
                            break;
                        case EXEC_COMMAND_AND_RANGE_ALL:
                            for (String url : db.getRangeUrls(urlSet, indexSeed, pFrom, pTo)) {
                                execCommand(command, url);
                                refreshConsole();
                            }
                            repaint();
                            break;
                        case ERROR_CONSOLE:
                            JOptionPane.showMessageDialog(null, ERROR_CONSOLE_STRING,
                                    ERROR_STRING, JOptionPane.ERROR_MESSAGE);
                            break;
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ERROR_CONSOLE_STRING, ERROR_STRING,
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            if (btn.equals(buttonClose))
                consoleFrame.closeConsole();
        }
    }

}
