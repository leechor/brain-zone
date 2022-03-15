package org.licho.brain.simuApi;

import org.licho.brain.simuApi.enu.ExperimentControlType;

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
