package org.licho.brain.brainApi;

/**
 *
 */
public interface IExecutionContext {
    IExecutionInformation ExecutionInformationValue();

    IBooleanTableStateColumn.ICalendar CalendarValue();

    IRandom Random();
}
