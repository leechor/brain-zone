package org.licho.brain.api;

/**
 *
 */
public interface IElementData {
    IPropertyReaders getProperties();

    IStates getStates();

    IEvents getEvents();

    IExecutionContext getExecutionContext();
}
