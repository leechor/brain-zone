package org.licho.brain.concrete;


import org.licho.brain.concrete.fake.TimeSpan;
import org.licho.brain.utils.simu.system.DateTime;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class SomeRun {
    private double now;
    private DateTime startTime;
    private DateTime endTime;
    private boolean bool_2 = true;
    private double times = 1.0;
    private double duration;
    private boolean mayBeRun;
    private int int_5;
    private double double_2;
    private Object object_0;
    private int int_2;
    private List<CalendarItem> calendarItems;
    private AboutRunOperatorsList aboutRunOperatorsList = new AboutRunOperatorsList();
    private CalendarItem _calendarItem0;
    private CalendarItem calendarItem;
    private int aNtnNaCiop;
    private boolean bool_0;
    private boolean bool_1;
    private int int_1;
    private long ulong_0;
    private int int_3;
    private boolean bool_6;
    private RunOperatorWrapper runOperatorWrapper;
	private List<CalendarItem> aboutRunOperators;

    public void setEndTime(DateTime endTime) {
        if (endTime.compare(this.getNowTime()) < 0) {
            throw new IllegalArgumentException();
        }
        this.endTime = endTime;
        this.duration = (this.endTime.sub(this.startTime)).TotalHours();
        this.method_22();
    }

    private void method_22() {
        // TODO: 2021/12/17 
    }

    public DateTime getNowTime() {
        DateTime startTime = this.startTime;
        if (!this.bool_2) {
            startTime = startTime.add(TimeSpan.FromHours((this.times - 1.0) * this.duration));
        }
        return startTime.add(TimeSpan.FromHours(this.now - this.getPassTime()));
    }

    public double getPassTime() {
        if (this.duration == Double.MAX_VALUE) {
            return 0.0;
        }
        if (this.bool_2) {
            return 0.0;
        }
        return (this.times - 1.0) * this.duration;
    }

    public void method_9(boolean isInfinite) {
    }

    public double TimeNow() {
        return this.now;
    }

    public boolean beRun() {
        return this.mayBeRun;
    }

    public void markNotRun(Object o) {
        this.mayBeRun = false;
    }

    public void method_27(int value) {
        this.int_5 = Math.max(0, value);
    }

    public void method_21() {
        this.now = this.getPassTime();
        this.double_2 = Double.MAX_VALUE;
        this.mayBeRun = false;
        this.object_0 = null;
        this.int_2 = 0;
        this.calendarItems.clear();
        this.aboutRunOperatorsList.clear();
        this._calendarItem0 = null;
        this.aNtnNaCiop = 0;
        this.bool_0 = false;
        this.bool_1 = false;
        this.int_1 = 0;
        this.ulong_0 = 0L;
        this.int_3 = 0;
        this.bool_6 = false;
        this.method_22();
        this.calendarItem = null;
    }

    public RunOperatorWrapper getRunOperatorWrapper() {
        return this.runOperatorWrapper;
    }

    public CalendarItem getAboutRunOperator() {
        return this.calendarItem;
    }

    public void method_42(CalendarItem calendarItem) {
        calendarItem.index = 4;
        this.method_47(calendarItem);
    }

    public void method_47(CalendarItem calendarItem) {
        calendarItem.ulong_0 = this.getNext();
        calendarItem.time = this.now;
        calendarItem.int_1 = -1;
        calendarItem.bool_0 = true;
        this.aboutRunOperatorsList.method_0(calendarItem, false);
    }

    private Long getNext() {
        if (this.ulong_0 == Long.MAX_VALUE) {
            this.incrementIndex();
        }
        return this.ulong_0 += 1L;
    }

    private void incrementIndex() {
        this.ulong_0 = 0L;
		this.ulong_0 = this.aboutRunOperatorsList.getNext(this.ulong_0);
        List<CalendarItem> calendarItems = new ArrayList<>(this.aboutRunOperators);
        calendarItems.sort(CalendarItem::compareTo);
        for (int i = calendarItems.size() - 1; i >= 0; i--) {
            CalendarItem calendarItem = this.aboutRunOperators.get(i);
            calendarItem.ulong_0 = this.ulong_0;
            this.ulong_0 = this.ulong_0 + 1L;
        }
    }

    @FunctionalInterface
    public interface RunEventHandler extends Runnable {
    }
}
