package com.zdpx.cctpp.simuApi;

/**
 *
 */
public interface IEventDefinitions extends INamedSimioCollection<IEventDefinition> {
    IEventDefinition addEvent(String name);
}
