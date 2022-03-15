package org.licho.brain.brainApi;

import org.licho.brain.brainApi.enu.ExperimentParameterType;

/**
 *
 */
public interface IExperimentParameter {
    String Name();

    ExperimentParameterType Type();

    String Value();

    void setValue(String value);
}
