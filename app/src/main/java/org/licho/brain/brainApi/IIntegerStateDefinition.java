package org.licho.brain.brainApi;

/**
 *
 */
public interface IIntegerStateDefinition extends IStateDefinition {
    int getInitialValue();

    void setInitialValue(int initialValue);
}
