package org.licho.brain.brainApi;

/**
 * An interface for manipulating a state definition that holds a boolean value.
 */
public interface IBooleanStateDefinition extends IStateDefinition {
    boolean getInitialValue();

    void setInitialValue(boolean value);
}
