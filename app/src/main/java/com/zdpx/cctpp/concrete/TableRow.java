package com.zdpx.cctpp.concrete;

/**
 *
 */
public class TableRow {
    private final Table table;
    private final int row;

    TableRow(Table table, int row) {
        this.table = table;
        this.row = row;
    }

    public Table Table() {
        return this.table;
    }

    public int Row() {
        return this.row;
    }
}
