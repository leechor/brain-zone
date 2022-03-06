package com.zdpx.cctpp.simuApi;

/**
 *
 */
public interface IExecutionContext {
    IExecutionInformation ExecutionInformationValue();

    IBooleanTableStateColumn.ICalendar CalendarValue();

    IRandom Random();
}
