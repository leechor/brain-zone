package org.licho.brain.api;

/**
 *
 */
public interface IExecutionContext {
    IExecutionInformation ExecutionInformationValue();

    IBooleanTableStateColumn.ICalendar CalendarValue();

    IRandom Random();
}
