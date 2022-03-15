package org.licho.brain.simuApi;

/**
 *
 */
public interface ICalendar {
    double TimeNow();

    void ScheduleEvent(double time, Object eventObject, CalendarEventHandler callback);

}
