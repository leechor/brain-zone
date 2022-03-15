package org.licho.brain.api;

/**
 *
 */
public interface IScenarios extends INamedSimioCollection<IScenario>, INamedMutableSimioCollection<IScenario> {
    IScenario CreateScenario(String Name);
}
