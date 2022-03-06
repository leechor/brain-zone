package com.zdpx.cctpp.simuApi;

import com.zdpx.cctpp.simuApi.enu.ExperimentParameterType;

/**
 *
 */
public interface IExperimentParameter {
    String Name();

    ExperimentParameterType Type();

    String Value();

    void setValue(String value);
}
