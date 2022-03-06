package com.zdpx.cctpp.simuApi;

/**
 *
 */
public interface IElementData {
    IPropertyReaders getProperties();

    IStates getStates();

    IEvents getEvents();

    IExecutionContext getExecutionContext();
}
