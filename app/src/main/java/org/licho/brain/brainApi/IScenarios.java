package org.licho.brain.brainApi;

/**
 *
 */
public interface IScenarios extends INamedSimioCollection<IScenario>, INamedMutableSimioCollection<IScenario> {
    IScenario CreateScenario(String Name);
}
