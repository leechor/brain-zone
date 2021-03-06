package org.licho.brain.api;

import org.licho.brain.utils.simu.system.DateTime;

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
