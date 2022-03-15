package org.licho.brain.api;

/**
 *
 */
public interface ITableStateColumnPlanningSettings {
    boolean isVisibleInTables();

    void setVisibleInTables(boolean visible);

    String getCategoryNameInTables();

    void setCategoryNameInTables(String categoryName);
}
