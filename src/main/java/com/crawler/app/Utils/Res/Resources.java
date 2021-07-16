package com.crawler.app.Utils.Res;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import javax.imageio.ImageIO;

public class Resources {

    /**
     * La classe di riferimento dalla quale leggere le risorse
     */
    static Class source = Resources.class;

    /**
     * Costruttore privato (Resources è una classe statica)
     */
    private Resources() {
    }

    /**
     * Restituisce un'immagine presente nelle risorse dell'applicazione
     *
     * @param path Il path relativo dell'immagine (ad esempio:
     *             "/path/myImage.png")
     * @return L'immagine corrispondente al path
     */
    public static BufferedImage getImage(String path) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(getResource(path));
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
            System.exit(-1);
        }
        return image;
    }

    /**
     * Chiama il metodo getResource() sulla classe di riferimento
     *
     * @see java.lang.Class#getResource(java.lang.String)
     */
    public static URL getResource(String resource) {
        return Resources.source.getResource(resource);
    }

}
