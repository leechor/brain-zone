package org.licho.brain.api;

import org.licho.brain.api.enu.ResponseObjective;

/**
 *
 */
public interface IExperimentResponse {
    String getName();

    String getExpression();

    void setExpression(String expression);

    double getLowerBound();

    void setLowerBound(double lowerBound);

    double getUpperBound();

    void setUpperBound(double upperBound);

    ResponseObjective getObjective();

    void setResponseObjective(ResponseObjective responseObjective);

    boolean getPrimary();

    IExperiment getExperiment();

    IExperimentParameters getParameters();
}
