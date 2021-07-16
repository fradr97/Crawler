package com.crawler.app.Config;

import java.awt.*;

import static com.crawler.app.Config.Bounds.*;
import static com.crawler.app.Config.Strings.*;

public final class Fonts {

    private Fonts() { }

    public static final Font FONT_LABEL_WINDOW = new Font(ARIAL, Font.ITALIC, SPLASH_SCREEN_HEIGHT / 17);
    public static final Font FONT_TITLE_WELCOME = new Font(ALGERIAN, Font.ITALIC, SPLASH_SCREEN_HEIGHT / 5 + 10);
    public static final Font FONT_TITLE_WELCOME_SMALL = new Font(ALGERIAN, Font.ITALIC, SPLASH_SCREEN_HEIGHT / 6 + 10);
    public static final Font FONT_LABEL_PANEL = new Font(ALGERIAN, Font.ITALIC, SPLASH_SCREEN_HEIGHT / 6 + 10);
    public static final Font FONT_LABEL_PANEL_SMALL = new Font(ALGERIAN, Font.ITALIC, SPLASH_SCREEN_HEIGHT / 7 + 10);
    public static final Font FONT_BAR = new Font(ARIAL, Font.PLAIN, FRAME_HEIGHT / 47);
    public static final Font FONT_LABEL = new Font(ARIAL, Font.BOLD, FRAME_HEIGHT / 45);
    public static final Font FONT_FIELD = new Font(ARIAL, Font.PLAIN, FRAME_HEIGHT / 45);
    public static final Font FONT_TABLE = new Font(ARIAL, Font.ITALIC, TABLE_HEIGHT / 35);
    public static final Font FONT_AREA = new Font(ARIAL, Font.ITALIC, FRAME_HEIGHT / 46);
    public static final Font FONT_LABEL_CONSOLE = new Font(ARIAL, Font.ITALIC, FRAME_CONSOLE_HEIGHT / 28);

}
