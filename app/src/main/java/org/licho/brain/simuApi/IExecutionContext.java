package org.licho.brain.simuApi;

/**
 *
 */
public interface IExecutionContext {
    IExecutionInformation ExecutionInformationValue();

    IBooleanTableStateColumn.ICalendar CalendarValue();

    IRandom Random();
}
