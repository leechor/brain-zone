package com.zdpx.cctpp.concrete;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class AboutRunOperatorsList {
    @SuppressWarnings("unchecked")
    private final List<CalendarItem>[] aboutRunOperators = new ArrayList[5];
    private final boolean[] bool_0 = new boolean[5];
    private int count;

    public AboutRunOperatorsList() {
        this.aboutRunOperators[0] = new ArrayList<>(16);
        this.aboutRunOperators[1] = new ArrayList<>(16);
        this.aboutRunOperators[2] = new ArrayList<>(16);
        this.aboutRunOperators[3] = new ArrayList<>(16);
        this.aboutRunOperators[4] = new ArrayList<>(16);
    }


    public void method_0(CalendarItem calendarItem, boolean param1) {
        int index = calendarItem.index;
        List<CalendarItem> runOperators = this.aboutRunOperators[index];
        if (param1) {
            runOperators.add(calendarItem);
            if (runOperators.size() > 1) {
                this.bool_0[index] = true;
            }
        } else {
            runOperators.add(0, calendarItem);
        }
        this.count++;
    }

    public CalendarItem method_1() {
        for (int i = this.aboutRunOperators.length - 1; i >= 0; i--) {
            List<CalendarItem> runOperators = this.aboutRunOperators[i];
            if (runOperators.size() > 0) {
                if (this.bool_0[i]) {
                    runOperators.sort(CalendarItem::compareTo);
                    this.bool_0[i] = false;
                }
                CalendarItem calendarItem = runOperators.get(runOperators.size() - 1);
                runOperators.remove(runOperators.size() - 1);
                this.count--;
                return calendarItem;
            }
        }
        return null;
    }

    public void method_2(CalendarItem calendarItem) {
        List<CalendarItem> calendarItems = this.aboutRunOperators[calendarItem.index];
        calendarItems.remove(calendarItem);
        this.count--;
    }

    public int Count() {
        return this.count;
    }

    public void clear() {
        for (List<CalendarItem> runOperators : this.aboutRunOperators) {
            runOperators.clear();
        }
        this.count = 0;
    }

    public long getNext(long param0) {
        for (List<CalendarItem> runOperators : this.aboutRunOperators) {
            for (int j = runOperators.size() - 1; j >= 0; j--) {
                CalendarItem calendarItem = runOperators.get(j);
                calendarItem.ulong_0 = param0;
                param0 = param0 + 1L;
            }
        }
        return param0;
    }
}
