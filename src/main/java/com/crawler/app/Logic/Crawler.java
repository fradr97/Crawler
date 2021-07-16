package com.crawler.app.Logic;

import com.crawler.app.Config.Strings;
import com.crawler.app.MongoDB.MongoDBConnection;

import java.io.IOException;

import com.crawler.app.Utils.GenericsMethods;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import static com.crawler.app.Config.Strings.*;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import org.bson.Document;

public class Crawler {

    private MongoDBConnection db;
    private RobotsTxt robots;

    private Document seed;

    private final List<String> frontierUrls = new ArrayList<>();
    private final List<String> visitedUrls = new ArrayList<>();

    private boolean stopCrawling = false;

    private int extracts; /** url estratti ad ogni iterata */

    public void setCrawling(String firstUrl, int weight, JTextArea area) {
        this.db = new MongoDBConnection();
        MongoDBConnection.getConnectionDB();

        boolean connection = GenericsMethods.checkInternetConnection();

        if (connection) {
            this.robots = new RobotsTxt(firstUrl);
            area.setText(EMPTY_STRING);

            if (this.robots.isAllow(firstUrl)) {
                int idUrl = this.db.countElementsInCollection() + 1;
                this.seed = new Document(ID_SEED, idUrl).append(SEED, firstUrl);

                this.frontierUrls.add(firstUrl);

                this.startCrawling(weight, area);
            } else {
                JOptionPane.showMessageDialog(null, INVALID_URL_ROBOTS_STRING, ERROR_STRING,
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, INTERNET_NOT_CONNECTED_STRING, ERROR_STRING,
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void startCrawling(int weight, JTextArea area) {
        while (!this.frontierUrls.isEmpty() && this.visitedUrls.size() < weight && !this.stopCrawling) {

            String url = this.frontierUrls.iterator().next();

            if (this.robots.isAllow(url)) {
                area.setText(area.getText().concat(NEXT_LINE_STRING + FOUR_SPACES + url + NEXT_LINE_STRING));

                try {
                    org.jsoup.nodes.Document document = Jsoup.connect(url).get();

                    Elements links = document.select(Strings.A_HREF);
                    this.extracts = 0;

                    /**
                     * Mette tutti gli url estatti nella frontiera
                     * eccetto quelli bloccati dal robot!
                     */
                    for (Element link : links) {
                        if (this.robots.isAllow(link.attr(ABS_HREF)))
                            this.addFrontier(link.attr(ABS_HREF));
                    }
                    area.setText(area.getText().concat(FOUR_SPACES + this.extracts + NEXT_LINE_STRING));
                } catch (IOException ex) {
                    area.setText(area.getText().concat(FOUR_SPACES + PROBLEM_URL_STRING + NEXT_LINE_STRING));
                }

                this.frontierUrls.remove(url);
                this.visitedUrls.add(url);

                area.setText(area.getText().concat(FOUR_SPACES + this.visitedUrls.size() + NEXT_LINE_STRING
                        + FOUR_SPACES + this.frontierUrls.size() + NEXT_LINE_STRING2));
            }
        }

        this.seed.append(VISITED, this.visitedUrls)
                .append(CRAWL_FRONTIER, this.frontierUrls);

        this.db.insertUrls(this.seed);

        this.frontierUrls.clear();
        this.visitedUrls.clear();
    }

    public void addFrontier(String url) {
        if (url != null && !url.isEmpty() && !this.frontierUrls.contains(url)
                && !this.visitedUrls.contains(url)) {
            this.frontierUrls.add(url);
            this.extracts++;
        }
    }

    public void stopCrawling() {
        this.stopCrawling = true;
    }
}