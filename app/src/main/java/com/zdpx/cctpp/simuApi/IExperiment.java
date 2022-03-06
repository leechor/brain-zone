package com.zdpx.cctpp.simuApi;

/**
 *
 */
public interface IExperiment {
    String getName();

    IModel getModel();

    IRunSetup getRunSetup();
}
