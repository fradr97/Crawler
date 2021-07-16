package com.crawler.app.Utils.MenuBar;

import javax.swing.JMenuBar;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import static com.crawler.app.Config.Bounds.*;
import static com.crawler.app.Config.Strings.*;
import static com.crawler.app.Config.Fonts.*;

public class MenuBar extends JMenuBar {

    public final JMenuBar bar = new JMenuBar();

    public final JMenuItem itemNew = new JMenu(NEW);
    public final JMenuItem itemExit = new JMenu(EXIT);
    public final JMenuItem itemDeleteOne = new JMenu(DELETE_ONE);
    public final JMenuItem itemDeleteAll = new JMenu(DELETE_ALL);
    public final JMenuItem itemHistory = new JMenu(HISTORY);

    public MenuBar(JFrame frame) {
        this.bar.setPreferredSize(BAR_DIMENSION);
        frame.setJMenuBar(this.bar);

        JMenu file = new JMenu(FILE);
        this.bar.add(file);
        JMenu edit = new JMenu(EDIT);
        this.bar.add(edit);
        JMenu view = new JMenu(VIEW);
        this.bar.add(view);

        file.add(this.itemNew);
        file.add(this.itemExit);

        edit.add(this.itemDeleteOne);
        edit.add(this.itemDeleteAll);

        view.add(this.itemHistory);

        file.setFont(FONT_BAR);
        edit.setFont(FONT_BAR);
        view.setFont(FONT_BAR);
        this.itemNew.setFont(FONT_BAR);
        this.itemExit.setFont(FONT_BAR);
        this.itemDeleteOne.setFont(FONT_BAR);
        this.itemDeleteAll.setFont(FONT_BAR);
        this.itemHistory.setFont(FONT_BAR);
    }
}
