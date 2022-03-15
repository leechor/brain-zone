package org.licho.brain.simuApi;

import org.licho.brain.simuApi.enu.TimeUnit;

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
