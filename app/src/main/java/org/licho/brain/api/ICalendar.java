package org.licho.brain.api;

/**
 *
 */
public interface ICalendar {
    double TimeNow();

    void ScheduleEvent(double time, Object eventObject, CalendarEventHandler callback);

}
