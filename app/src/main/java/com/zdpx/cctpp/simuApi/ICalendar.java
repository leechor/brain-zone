package com.zdpx.cctpp.simuApi;

/**
 *
 */
public interface ICalendar {
    double TimeNow();

    void ScheduleEvent(double time, Object eventObject, CalendarEventHandler callback);

}
