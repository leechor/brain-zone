package com.zdpx.cctpp.simuApi;

import cn.hutool.core.date.DateTime;

/**
 *
 */
public interface IResourceCapacityRecord extends IRuntimeLogRecord {
    DateTime getStartTime();

    DateTime getEndTime();

    String getResourceId();

    String getResourceName();

    double getCapacityScheduled();

    double getCapacityAllocated();

    double getCapacityUtilized();
}
