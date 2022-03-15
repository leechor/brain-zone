package org.licho.brain.simuApi;

/**
 *
 */
public interface IEventDefinitions extends INamedSimioCollection<IEventDefinition> {
    IEventDefinition addEvent(String name);
}
