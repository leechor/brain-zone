package org.licho.brain.simuApi;

import org.licho.brain.simuApi.enu.ExperimentParameterType;

/**
 *
 */
public interface IExperimentParameter {
    String Name();

    ExperimentParameterType Type();

    String Value();

    void setValue(String value);
}
