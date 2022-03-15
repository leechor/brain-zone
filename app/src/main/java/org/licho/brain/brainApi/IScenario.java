package org.licho.brain.brainApi;

/**
 *
 */
public interface IScenario {
    String getName();

    boolean getActive();

    void setActive(boolean active);

    int getReplicationsRequired();

    void setReplicationsRequired(int replicationRequired);

    int getReplicationsCompleted();

    IExperiment getExperiment();

    boolean SetControlValue(IExperimentControl iExperimentControl, String valueString);

    boolean GetControlValue(IExperimentControl iExperimentControl, String valueString);

    boolean GetResponseValue(IExperimentResponse iExperimentResponse, double responseValue);

    boolean GetResponseValueForReplication(IExperimentResponse iExperimentResponse, int replicationNumber,
                                           double responseValue);

    IControlValues getControlValues();

    IResponseValues getResponseValues();

    IConstraintValues getConstraintValuesValue();

    boolean SatisfiesAllConstraints();
}
