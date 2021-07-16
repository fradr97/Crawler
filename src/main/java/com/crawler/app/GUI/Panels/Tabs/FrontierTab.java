package com.crawler.app.GUI.Panels.Tabs;

import static com.crawler.app.Config.Strings.*;

import java.util.List;

public class FrontierTab extends Tab {
    public FrontierTab(int indexSeed) {
        super(FRONTIER.toUpperCase());

        this.getFrontier(indexSeed);
    }

    public void getFrontier(int index) {
        List<String> list;

        if (this.db.checkCollectionExists()) {
            list = this.db.getUrls(CRAWL_FRONTIER, index);

            for (int i = 0; i < list.size(); i++) {
                this.tableModel.addRow(new Object[]{i + 1, list.get(i)});
            }
        }
    }

}
