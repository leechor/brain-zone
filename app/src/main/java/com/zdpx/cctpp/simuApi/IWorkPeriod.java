package com.zdpx.cctpp.simuApi;

import java.time.Duration;

/**
 *
 */
public interface IWorkPeriod {
    Duration getStart();

    void setStart(Duration start);

    Duration getEnd();

    void setEnd(Duration end);

    double getValue();

    void setValue(double value);

    String getDescription();

    void setDescription(String description);
}
