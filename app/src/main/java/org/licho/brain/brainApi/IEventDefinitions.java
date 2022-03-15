package org.licho.brain.brainApi;

/**
 *
 */
public interface IEventDefinitions extends INamedSimioCollection<IEventDefinition> {
    IEventDefinition addEvent(String name);
}
