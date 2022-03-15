package org.licho.brain.brainApi;

/**
 *
 */
public interface IRealStateDefinition extends IUnitizedStateDefinition {
    double getInitialValue();

    void setInitialValue(double initialValue);
}
