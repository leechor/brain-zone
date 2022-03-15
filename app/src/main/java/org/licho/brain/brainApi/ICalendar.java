package org.licho.brain.brainApi;

/**
 *
 */
public interface ICalendar {
    double TimeNow();

    void ScheduleEvent(double time, Object eventObject, CalendarEventHandler callback);

}
