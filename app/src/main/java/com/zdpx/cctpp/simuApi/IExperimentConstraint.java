package com.zdpx.cctpp.simuApi;

/**
 *
 */
public interface IExperimentConstraint {
    String getName();

    String getExpression();

    void setExpression(String expression);

    double getLowerBound();

    void setLowerBound(double lowerBound);

    double getUpperBound();

    void setUpperBound(double upperBound);

    IExperiment getExperiment();
}
