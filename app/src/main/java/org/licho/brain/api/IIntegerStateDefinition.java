package org.licho.brain.api;

/**
 *
 */
public interface IIntegerStateDefinition extends IStateDefinition {
    int getInitialValue();

    void setInitialValue(int initialValue);
}
