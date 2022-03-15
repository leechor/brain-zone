package org.licho.brain.concrete;

import org.licho.brain.enu.StatisticConfidenceIntervalType;

/**
 *
 */
public interface ISimulationRun {
    String ProjectName();

    String ProjectFolder();

    String ModelName();

    String ExperimentName();

    String ScenarioName();

    int ReplicationNumber();

    int ReplicationsRequired();

    StatisticConfidenceIntervalType ConfidenceLevel();

    boolean imethod_0(Warning warning);

    double imethod_1();

    double imethod_2();

    UnitConversions UnitConversions();
}
