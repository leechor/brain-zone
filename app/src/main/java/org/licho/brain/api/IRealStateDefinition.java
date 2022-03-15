package org.licho.brain.api;

/**
 *
 */
public interface IRealStateDefinition extends IUnitizedStateDefinition {
    double getInitialValue();

    void setInitialValue(double initialValue);
}
