package org.licho.brain.api;

import org.licho.brain.api.enu.TimeUnit;

/**
 *
 */
public interface IRateTable {
    String getName();

    void setName(String name);

    double getIntervalSize();

    void setIntervalSize(double intervalSize);

    TimeUnit getIntervalSizeUnit();

    void setIntervalSizeUnit(TimeUnit intervalSizeUnit);

    int getNumberOfIntervals();

    void setNumberOfIntervals(int numberOfIntervals);

    IRateTableIntervals getIntervals();
}
