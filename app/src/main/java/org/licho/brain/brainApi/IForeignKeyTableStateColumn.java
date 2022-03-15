package org.licho.brain.brainApi;

/**
 *
 */
public interface IForeignKeyTableStateColumn extends ITableStateColumn {
    String getTableKey();

    void setTableKey(String tableKey);
}
