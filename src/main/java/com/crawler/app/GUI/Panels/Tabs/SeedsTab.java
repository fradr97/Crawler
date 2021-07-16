package com.crawler.app.GUI.Panels.Tabs;

import static com.crawler.app.Config.Strings.*;

public class SeedsTab extends Tab {
    public SeedsTab(int indexSeed) {
        super(SEED.toUpperCase());

        this.getSeed(indexSeed);
    }

    public void getSeed(int index) {
        int i = 0;

        if (this.db.checkCollectionExists()) {
            while (i < this.db.countElementsInCollection()) {
                if (this.db.getAllSeeds().get(i).idSeed == index)
                    this.tableModel.addRow(new Object[]{this.db.getAllSeeds().get(i).idSeed,
                            this.db.getAllSeeds().get(i).seed});
                i++;
            }
        }
    }
}