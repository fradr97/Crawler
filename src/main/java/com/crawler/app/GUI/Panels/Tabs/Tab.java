package com.crawler.app.GUI.Panels.Tabs;

import com.crawler.app.MongoDB.MongoDBConnection;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

import static com.crawler.app.Config.Bounds.*;
import static com.crawler.app.Config.Colors.DEFAULT_COLOR;
import static com.crawler.app.Config.Fonts.FONT_TABLE;
import static com.crawler.app.Config.Strings.COLUMN_ID;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;

public abstract class Tab extends JComponent {

    public final MongoDBConnection db;
    public final DefaultTableModel tableModel;

    public Tab(String columnName) {
        this.setSize(TAB_DIMENSION, TAB_DIMENSION);
        this.setLayout(null);

        this.db = new MongoDBConnection();
        MongoDBConnection.getConnectionDB();

        this.tableModel = new DefaultTableModel();
        String[] columnNames = {COLUMN_ID.toUpperCase(), columnName};
        this.tableModel.setColumnIdentifiers(columnNames);

        JTable table = new JTable();
        table.setModel(this.tableModel);
        table.setBounds(DEFAULT_X, DEFAULT_Y, FRAME_WIDTH, FRAME_HEIGHT);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        table.setFont(FONT_TABLE);
        table.setRowHeight(30);
        table.setShowHorizontalLines(false);
        table.setShowVerticalLines(false);
        table.setShowGrid(false);

        JScrollPane jScrollPane = new JScrollPane(table);
        jScrollPane.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setBounds(DEFAULT_X, DEFAULT_Y, TABLE_WIDTH, TABLE_HEIGHT);

        this.add(jScrollPane);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(DEFAULT_COLOR);
        g.fillRect(DEFAULT_X, DEFAULT_Y, TAB_DIMENSION, TAB_DIMENSION);
    }

    public void resetSeeds() {
        for (int i = this.tableModel.getRowCount() - 1; i >= 0; i--) {
            this.tableModel.removeRow(i);
        }
    }
}
