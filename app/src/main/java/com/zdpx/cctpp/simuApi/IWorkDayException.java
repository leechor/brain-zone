package com.zdpx.cctpp.simuApi;


import com.zdpx.cctpp.utils.simu.system.DateTime;

/**
 *
 */
public interface IWorkDayException {
    DateTime Date();

    void Date(DateTime date);

    IDayPattern DayPattern();

    void DayPattern(IDayPattern dayPattern);

    String Description();

    void Description(String description);
}
