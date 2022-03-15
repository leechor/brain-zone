package org.licho.brain.brainApi;


import org.licho.brain.concrete.fake.TimeSpan;
import org.licho.brain.utils.simu.system.DateTime;

/**
 *
 */
public interface IRunSetup {
    DateTime StartTime();

    void StartTime(DateTime startTime);

    DateTime EndTime();

    void EndTime(DateTime endTime);

    TimeSpan WarmupPeriod();

    void WarmupPeriod(TimeSpan warmupPeriod);
}
