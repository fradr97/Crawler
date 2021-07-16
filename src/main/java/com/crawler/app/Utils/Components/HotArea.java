package com.crawler.app.Utils.Components;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class HotArea extends Rectangle {
    private final transient Image image;
    private final transient Image imageHover;

    private boolean isHover = false;

    public HotArea(Image pImage, Image pImageHover, int pX, int pY, int pWidth, int pHeight) {
        this.x = pX;
        this.y = pY;
        this.width = pWidth;
        this.height = pHeight;
        this.image = pImage;
        this.imageHover = pImageHover;
    }

    public void draw(Graphics g) {
        if (!isHover)
            g.drawImage(this.image, this.x, this.y, this.width, this.height, null);
        else
            g.drawImage(this.imageHover, this.x, this.y, this.width, this.height, null);
    }

    public void setHover(boolean bool) {
        this.isHover = bool;
    }
}