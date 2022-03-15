package org.licho.brain.brainApi;

import org.licho.brain.brainApi.enu.ExperimentControlType;

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
