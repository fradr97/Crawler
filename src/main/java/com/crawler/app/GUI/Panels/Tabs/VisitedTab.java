package com.crawler.app.GUI.Panels.Tabs;

import java.util.List;

import static com.crawler.app.Config.Strings.VISITED;

public class VisitedTab extends Tab {
    public VisitedTab(int indexSeed) {
        super(VISITED.toUpperCase());

        this.getVisited(indexSeed);
    }

    public void getVisited(int index) {
        List<String> list;

        if (this.db.checkCollectionExists()) {
            list = this.db.getUrls(VISITED, index);

            for (int i = 0; i < list.size(); i++) {
                this.tableModel.addRow(new Object[]{i + 1, list.get(i)});
            }
        }
    }

}
