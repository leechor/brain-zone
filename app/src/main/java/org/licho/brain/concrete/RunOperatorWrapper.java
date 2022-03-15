package org.licho.brain.concrete;

/**
 *
 */
public class RunOperatorWrapper {
    private CalendarItem calendarItem;
	private SomeRun someRun;

    public CalendarItem createCalendarItem()
	{
		return this.createCalendarItem(true);
	}

	public CalendarItem createCalendarItem(boolean param0)
	{
		CalendarItem calendarItem;
		if (this.calendarItem != null && this.calendarItem != this.someRun.getAboutRunOperator())
		{
			calendarItem = this.calendarItem;
			this.calendarItem = calendarItem.maybePrarent;
		}
		else
		{
			calendarItem = new CalendarItem();
		}
		calendarItem.init(param0);
		return calendarItem;
	}

		public void setAboutRunOperator(CalendarItem calendarItem)
	{
		calendarItem.initFirstInternal();
		calendarItem.maybePrarent = this.calendarItem;
		this.calendarItem = calendarItem;
	}


}
