package org.licho.brain.api;

/**
 *
 */
public interface ITableStateColumn extends IStateDefinition {
    /**
     * Retrieves settings for this table state column that apply only to Operational Planning mode. Returns null if
     * these settings are not available (i.e. planning mode is not enabled).
     *
     * @return
     */
    ITableStateColumnPlanningSettings getPlanningSettings();
}
