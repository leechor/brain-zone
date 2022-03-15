package org.licho.brain.simuApi;

/**
 *
 */
public interface IIntegerStateDefinition extends IStateDefinition {
    int getInitialValue();

    void setInitialValue(int initialValue);
}
