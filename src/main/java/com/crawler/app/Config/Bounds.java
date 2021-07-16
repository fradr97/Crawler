package com.crawler.app.Config;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Toolkit;

public final class Bounds {

    private Bounds() { }

    public static final int TIME_START = 2000;

    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    /** FRAMES */
    public static final int DEFAULT_X = 0;
    public static final int DEFAULT_Y = 0;

    public static final int SPLASH_SCREEN_WIDTH = screenSize.width / 4 + 30;
    public static final int SPLASH_SCREEN_HEIGHT = screenSize.height / 4 + 30;

    public static final int FRAME_WIDTH = screenSize.width - 250;
    public static final int FRAME_HEIGHT = screenSize.height - 150;

    public static final int FRAME_CONSOLE_WIDTH = screenSize.width / 3 + 60;
    public static final int FRAME_CONSOLE_HEIGHT = screenSize.height / 2 + 60;

    /** IMAGES */
    public static final int MINI_SPIDER_WIDTH = FRAME_WIDTH / 20;
    public static final int MINI_SPIDER_HEIGHT = FRAME_WIDTH / 20;
    public static final int MINI_SPIDER_X = 3;

    /** AREAS */
    private static final int AREAS_SPACE = 2;

    public static final int AREA_ATTRIBUTES_WIDTH = FRAME_WIDTH / 8 + 5;

    public static final int AREA_CRAWLING_WIDTH = FRAME_WIDTH / 2 + FRAME_WIDTH / 4 - AREA_ATTRIBUTES_WIDTH;
    public static final int AREA_CRAWLING_HEIGHT = FRAME_HEIGHT / 2;

    public static final int AREAS_CENTERED = centerHorizontalFrame(AREA_CRAWLING_WIDTH +
            AREA_ATTRIBUTES_WIDTH + AREAS_SPACE);

    public static final int AREA_CRAWLING_X = AREAS_CENTERED + AREAS_SPACE + AREA_ATTRIBUTES_WIDTH;
    public static final int AREA_CRAWLING_Y = FRAME_HEIGHT / 3;

    public static final int AREA_SEEDS_WIDTH = FRAME_WIDTH / 2 + 200;
    public static final int AREA_SEEDS_HEIGHT = FRAME_HEIGHT / 2 + 100;
    public static final int AREA_SEEDS_X = (FRAME_WIDTH - AREA_SEEDS_WIDTH) / 2;
    public static final int AREA_SEEDS_Y = (FRAME_HEIGHT - AREA_SEEDS_HEIGHT) / 2;

    /** LABELS */
    private static final int LABEL_SPACE = 80;

    public static final int LABEL_WELCOME_Y = MINI_SPIDER_HEIGHT + 2;
    public static final int LABEL_START_WELCOME_Y = FRAME_HEIGHT / 3 + 55;
    public static final int LABEL_EXIT_WELCOME_Y = LABEL_START_WELCOME_Y + (LABEL_SPACE * 2);

    public static final int LABELS_CRAWLING_Y = FRAME_HEIGHT / 18;
    public static final int LABEL_URL_X = AREAS_CENTERED;
    public static final int LABEL_WEIGHT_X = (AREA_CRAWLING_X + AREA_CRAWLING_WIDTH) - FRAME_WIDTH / 3;

    public static final int LABEL_NO_SEEDS_Y = AREA_SEEDS_Y + 20;

    public static final int LABELS_CONSOLE_X = 10;
    public static final int LABEL_COMMAND_EXEC_Y = 10;
    
    /** FIELDS */
    public static final int SMALL_FIELD_WITH = FRAME_WIDTH / 15;

    public static final int LARGE_FIELD_WIDTH = FRAME_WIDTH / 3;
    public static final int LARGE_FIELD_HEIGHT = FRAME_HEIGHT / 18;

    public static final int FIELD_URL_X = LABEL_URL_X;
    public static final int FIELD_URL_Y = LABELS_CRAWLING_Y * 2;
    public static final int FIELD_WEIGHT_X = LABEL_WEIGHT_X;
    public static final int FIELD_WEIGHT_Y = FIELD_URL_Y;

    public static final int FIELDS_CONSOLE_X = (FRAME_CONSOLE_WIDTH - LARGE_FIELD_WIDTH) / 2;
    public static final int FIELD_EXEC_COMMAND_Y = FRAME_CONSOLE_HEIGHT / 5;
    public static final int FIELD_TO_CONSOLE_X = FIELDS_CONSOLE_X + SMALL_FIELD_WITH + 20;

    /** MENU BAR & SUBMENU BAR */
    public static final Dimension BAR_DIMENSION = new Dimension(FRAME_WIDTH, FRAME_HEIGHT / 34);
    public static final Dimension SUBMENU_BAR_DIMENSION = new Dimension(FRAME_WIDTH, FRAME_HEIGHT / 26);

    /** BUTTONS - SUB BAR */
    public static final int BUTTONS_SUB_BAR_WIDTH = FRAME_WIDTH / 40;
    public static final int BUTTONS_SUB_BAR_HEIGHT = SUBMENU_BAR_DIMENSION.height - 2;

    private static final int SPACE_BUTTONS_SUB_BAR = BUTTONS_SUB_BAR_WIDTH / 2 + 7;

    public static final int BUTTON_BACK_X = FRAME_WIDTH / 350;
    public static final int BUTTON_BACK_Y = (SUBMENU_BAR_DIMENSION.height - BUTTONS_SUB_BAR_HEIGHT) / 3;

    public static final int BUTTON_LEFT_X = centerHorizontalFrame(BUTTONS_SUB_BAR_WIDTH) -
            SPACE_BUTTONS_SUB_BAR;
    public static final int BUTTON_LEFT_Y = BUTTON_BACK_Y;

    public static final int BUTTON_RIGHT_X = centerHorizontalFrame(BUTTONS_SUB_BAR_WIDTH) +
            SPACE_BUTTONS_SUB_BAR;
    public static final int BUTTON_RIGHT_Y = BUTTON_LEFT_Y;

    /** BUTTONS - CONSOLE */
    private static final int SPACE_BROWSER_BUTTONS = 80;

    public static final int BUTTONS_BROWSER_WIDTH = FRAME_WIDTH / 40;
    public static final int BUTTONS_BROWSER_HEIGHT = FRAME_HEIGHT / 27;
    public static final int BUTTONS_BROWSER_Y = FRAME_CONSOLE_HEIGHT / 10;

    public static final int BUTTON_FIREFOX_CONSOLE_X = (FRAME_CONSOLE_WIDTH - BUTTONS_BROWSER_WIDTH) / 2;
    public static final int BUTTON_EDGE_CONSOLE_X = BUTTON_FIREFOX_CONSOLE_X - SPACE_BROWSER_BUTTONS;
    public static final int BUTTON_CHROME_CONSOLE_X = BUTTON_FIREFOX_CONSOLE_X + SPACE_BROWSER_BUTTONS;

    public static final int BUTTONS_CONSOLE_WIDTH = FRAME_CONSOLE_WIDTH / 5;
    public static final int BUTTONS_CONSOLE_HEIGHT = FRAME_CONSOLE_HEIGHT / 11;
    public static final int BUTTONS_CONSOLE_Y = FRAME_CONSOLE_HEIGHT - BUTTONS_CONSOLE_HEIGHT - LABEL_SPACE;

    public static final int BUTTON_LINKS_CONSOLE_WIDTH = (BUTTONS_CONSOLE_WIDTH * 4) / 2;
    public static final int BUTTON_LINKS_CONSOLE_HEIGHT = BUTTONS_CONSOLE_HEIGHT / 2 + 5;
    public static final int BUTTON_LINKS_CONSOLE_Y = FIELD_EXEC_COMMAND_Y + LARGE_FIELD_HEIGHT + 10;

    public static final int BUTTON_LINKS_CONSOLE_X = centerHorizontalConsole(BUTTON_LINKS_CONSOLE_WIDTH);
    public static final int BUTTON_CONFIRM_CONSOLE_X = (FRAME_CONSOLE_WIDTH / 2) - BUTTONS_CONSOLE_WIDTH - LABEL_SPACE;
    public static final int BUTTON_CLOSE_CONSOLE_X = (FRAME_CONSOLE_WIDTH / 2) + LABEL_SPACE;

    public static final int BUTTON_REFRESH_DIMENSION = FRAME_CONSOLE_WIDTH / 32;
    public static final int BUTTON_REFRESH_CONSOLE_X = LABELS_CONSOLE_X + 5;
    public static final int BUTTON_REFRESH_CONSOLE_Y = FIELD_EXEC_COMMAND_Y + 12;

    /** TABS */
    public static final int TABBED_PANE_Y = FRAME_HEIGHT / 6;
    public static final int TABBED_PANE_WIDTH = FRAME_WIDTH - FRAME_WIDTH / 210;
    public static final int TABBED_PANE_HEIGHT = FRAME_HEIGHT - TABBED_PANE_Y - (FRAME_HEIGHT / 13) + 2;

    public static final int TAB_DIMENSION = FRAME_WIDTH;

    /** TABLES */
    public static final int TABLE_WIDTH = FRAME_WIDTH - 18;
    public static final int TABLE_HEIGHT = FRAME_HEIGHT - FRAME_HEIGHT / 3 + 15;

    /** RADIO BUTTONS */
    public static final int RADIO_BUTTON_WIDTH = FRAME_WIDTH / 8;
    public static final int RADIO_BUTTON_HEIGHT = FRAME_HEIGHT / 20;

    public static final int RADIO_BUTTONS_CONSOLE_X = FIELD_TO_CONSOLE_X + SMALL_FIELD_WITH + 50;

    /** PROGRESS BAR */
    public static final int PROGRESS_BAR_WIDTH = (FRAME_WIDTH) / 2 + (FRAME_WIDTH) / 40;
    public static final int PROGRESS_BAR_HEIGHT = (FRAME_HEIGHT) / 22;
    public static final int PROGRESS_BAR_X = (FRAME_WIDTH - PROGRESS_BAR_WIDTH) / 2;
    public static final int PROGRESS_BAR_Y = LABELS_CRAWLING_Y * 4;

    /** METHODS */
    public static int centerHorizontalFrame(int pWidth){
        return (FRAME_WIDTH - pWidth) / 2;
    }

    public static int centerHorizontalConsole(int pWidth){
        return (FRAME_CONSOLE_WIDTH - pWidth) / 2;
    }

    public static int getWidthLabel(JLabel label){
        return label.getFontMetrics(label.getFont()).stringWidth(label.getText() + 5);
    }

    public static int getHeightLabel(JLabel label){
        return label.getFontMetrics(label.getFont()).getMaxAdvance();
    }

}