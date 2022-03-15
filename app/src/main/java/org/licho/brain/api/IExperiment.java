package org.licho.brain.api;

/**
 *
 */
public interface IExperiment {
    String getName();

    IModel getModel();

    IRunSetup getRunSetup();
}
