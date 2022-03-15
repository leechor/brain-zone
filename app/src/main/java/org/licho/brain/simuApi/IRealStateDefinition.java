package org.licho.brain.simuApi;

/**
 *
 */
public interface IRealStateDefinition extends IUnitizedStateDefinition {
    double getInitialValue();

    void setInitialValue(double initialValue);
}
