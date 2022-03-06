package com.zdpx.cctpp.simuApi;

import com.zdpx.cctpp.simuApi.enu.ExperimentControlType;

/**
 *
 */
public interface IExperimentControl {
    String getName();

    ExperimentControlType getType();

    String getDefaultValueString();

    IExperiment getExperiment();

    IExperimentParameters getParameters();
}