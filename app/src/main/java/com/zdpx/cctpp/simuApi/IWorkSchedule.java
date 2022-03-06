package com.zdpx.cctpp.simuApi;


import com.zdpx.cctpp.utils.simu.system.DateTime;

/**
 *
 */
public interface IWorkSchedule {
    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    DateTime getStartDate();

    void setStartDate(DateTime startDate);

    IDayPatternReferences getDayPatternReferences();

    IWorkDayExceptions getWorkDayExceptions();

}
