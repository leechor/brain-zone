package org.licho.brain.concrete;

import org.licho.brain.api.CalendarEventHandler;
import org.licho.brain.api.IBooleanTableStateColumn;
import org.licho.brain.api.ICalendar;
import org.licho.brain.api.IExecutionContext;
import org.licho.brain.api.IExecutionInformation;
import org.licho.brain.api.IRandom;
import org.licho.brain.utils.simu.ITrace;

/**
 *
 */
public class ExecutionContext implements IExecutionContext {

    private ITrace trace;
    private IntelligentObjectRunSpace parent;
    private AbsPropertyObject target;
    private TokenRunSpace tokenRunSpace;

    protected ExecutionContext(TokenRunSpace tokenRunSpace, IntelligentObjectRunSpace intelligentObjectRunSpace,
                               AbsPropertyObject absPropertyObject, ITrace trace) {
        this.tokenRunSpace = tokenRunSpace;
        this.target = absPropertyObject;
        this.parent = intelligentObjectRunSpace;
        this.trace = trace;
    }


    @Override
    public IExecutionInformation ExecutionInformationValue() {
        return null;
    }

    @Override
    public IBooleanTableStateColumn.ICalendar CalendarValue() {
        return null;
    }

    @Override
    public IRandom Random() {
        return null;
    }


    public static class Calendar implements ICalendar {
        private ExecutionContext executionContext;
        private SomeRun someRun;

        public Calendar(ExecutionContext executionContext, SomeRun someRun) {
            this.executionContext = executionContext;
            this.someRun = someRun;
        }

        @Override
        public double TimeNow() {
            return this.someRun.TimeNow();
        }

        @Override
        public void ScheduleEvent(double time, Object eventObject, CalendarEventHandler callback) {
            // TODO: 2022/2/7
        }

        public static class CalendarEventHandlerWrapper {
            public CalendarEventHandlerWrapper(CalendarEventHandler calendarEventHandler, Object param1) {
                // TODO: 2022/2/7
            }
        }
    }

}
