package org.licho.brain.api;

import org.licho.brain.api.enu.ExperimentControlType;

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
