package com.zdpx.cctpp.simuApi;

import com.zdpx.cctpp.utils.simu.system.DateTime;

/**
 *
 */
public interface IWorkPeriodException {
    DateTime Start();

    void Start(DateTime value);

    DateTime End();

    void End(DateTime value);

    double Value();

    void Value(double value);

    String Description();

    void Description(String value);
}
