package com.zdpx.cctpp.simuApi;


import com.zdpx.cctpp.concrete.fake.TimeSpan;
import com.zdpx.cctpp.utils.simu.system.DateTime;

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
