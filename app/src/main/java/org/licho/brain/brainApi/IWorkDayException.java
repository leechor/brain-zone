package org.licho.brain.brainApi;


import org.licho.brain.utils.simu.system.DateTime;

/**
 *
 */
public interface IWorkDayException {
    DateTime Date();

    void Date(DateTime date);

    IDayPattern DayPattern();

    void DayPattern(IDayPattern dayPattern);

    String Description();

    void Description(String description);
}
