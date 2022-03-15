package org.licho.brain.api;

/**
 *
 */
public interface IForeignKeyTableStateColumn extends ITableStateColumn {
    String getTableKey();

    void setTableKey(String tableKey);
}
