package com.crawler.app.Utils.MenuBar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SubMenuBar extends Rectangle {
    private final Color color;

    public SubMenuBar(int pX, int pY, Dimension dimension, Color pColor) {
        this.x = pX;
        this.y = pY;
        this.width = dimension.width;
        this.height = dimension.height;
        this.color = pColor;
    }

    public void drawBar(Graphics g) {
        g.setColor(this.color);
        g.fillRect(this.x, this.y, this.width, this.height);
    }
}
