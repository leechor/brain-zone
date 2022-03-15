package org.licho.brain.simuApi;

/**
 *
 */
public interface IElementData {
    IPropertyReaders getProperties();

    IStates getStates();

    IEvents getEvents();

    IExecutionContext getExecutionContext();
}
