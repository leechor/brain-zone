package org.licho.brain.concrete;

import org.licho.brain.IFunction.Action;

/**
 *
 */
public class CalendarItem implements Comparable<CalendarItem> {
    public double time;
    public Action<CalendarItem> delegate4_0;
	public ExecutionContext.Calendar.CalendarEventHandlerWrapper CalendarEventHandlerWrapper;

    Integer index;
    public int int_1;
    public Long ulong_0;
    public boolean bool_0;
    public boolean bool_1;
    public CalendarItem maybePrarent;
    public int int_2 = -1;
    private boolean bool_2 = true;

    @Override
    public int compareTo(CalendarItem calendarItem) {
        int result;
        if ((result = this.index.compareTo(calendarItem.index)) != 0) {
            return result;
        }
        return -this.ulong_0.compareTo(calendarItem.ulong_0);
    }

    public void init(boolean param0) {
        this.index = 2;
        this.int_1 = -2;
        this.bool_0 = false;
//   todo     this.class826_0 = null;
        this.bool_1 = true;
        this.bool_2 = param0;
    }

    public void initFirstInternal() {
        this.initFirst();
    }

    private void initFirst() {
        this.int_1 = -2;
//	todo	this.class826_0 = null;
//		this.delegate4_0 = null;
    }
}
