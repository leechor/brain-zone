package org.licho.brain.brainApi;

/**
 *
 */
public interface IExperiment {
    String getName();

    IModel getModel();

    IRunSetup getRunSetup();
}
