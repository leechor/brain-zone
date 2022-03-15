package org.licho.brain.brainApi;

/**
 *
 */
public interface IDayPattern {
    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    IWorkPeriods getWorkPeriods();
}
