package org.licho.brain.brainApi;

/**
 *
 */
public interface IExecutionInformation {
    void reportError(String error);

    void traceInformation(String information);

    String getProjectName();

    String getProjectFolder();

    String getModelName();

    String getExperimentName();

    String getScenarioName();

    int getReplicationNumber();
}
