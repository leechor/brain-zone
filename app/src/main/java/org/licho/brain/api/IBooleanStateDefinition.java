package org.licho.brain.api;

/**
 * An interface for manipulating a state definition that holds a boolean value.
 */
public interface IBooleanStateDefinition extends IStateDefinition {
    boolean getInitialValue();

    void setInitialValue(boolean value);
}
