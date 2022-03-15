package org.licho.brain.brainApi;

/**
 *
 */
public interface IElementData {
    IPropertyReaders getProperties();

    IStates getStates();

    IEvents getEvents();

    IExecutionContext getExecutionContext();
}
