package com.zdpx.cctpp.simuApi;

import com.zdpx.cctpp.concrete.CalendarEventHandler;

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
