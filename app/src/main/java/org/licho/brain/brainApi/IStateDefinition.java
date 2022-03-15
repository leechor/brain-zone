package org.licho.brain.brainApi;

/**
 *
 */
public interface IStateDefinition {

    String getName();

    String Description();

    void Description(String description);

    String getDisplayName();

    void setDisplayName(String displayName);

    boolean isAutoResetWhenStatisticsCleared();

    /**
     * Specifies if this state's value will automatically be reset to its initial value when statistics are cleared.
     * @param autoReset
     */
    void setAutoResetWhenStatisticsCleared(boolean autoReset);
}
