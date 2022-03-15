package org.licho.brain.simuApi;

/**
 *
 */
public interface IForeignKeyTableStateColumn extends ITableStateColumn {
    String getTableKey();

    void setTableKey(String tableKey);
}
