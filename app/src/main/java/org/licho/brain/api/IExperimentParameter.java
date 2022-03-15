package org.licho.brain.api;

import org.licho.brain.api.enu.ExperimentParameterType;

/**
 *
 */
public interface IExperimentParameter {
    String Name();

    ExperimentParameterType Type();

    String Value();

    void setValue(String value);
}
