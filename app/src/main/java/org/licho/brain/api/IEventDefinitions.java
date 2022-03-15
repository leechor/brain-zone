package org.licho.brain.api;

/**
 *
 */
public interface IEventDefinitions extends INamedSimioCollection<IEventDefinition> {
    IEventDefinition addEvent(String name);
}
