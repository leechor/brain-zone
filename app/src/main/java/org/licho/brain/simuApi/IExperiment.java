package org.licho.brain.simuApi;

/**
 *
 */
public interface IExperiment {
    String getName();

    IModel getModel();

    IRunSetup getRunSetup();
}
