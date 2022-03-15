package org.licho.brain.brainApi;

import org.licho.brain.concrete.CalendarEventHandler;

/**
 *
 */
public interface IBooleanTableStateColumn extends IBooleanStateDefinition, ITableStateColumn{

    /**
     *
     */
    interface ICalendar {
            double getTimeNow();

            void scheduleEvent(double time, Object eventObject, CalendarEventHandler callback);
    }
}
