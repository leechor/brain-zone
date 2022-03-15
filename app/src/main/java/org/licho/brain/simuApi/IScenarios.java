package org.licho.brain.simuApi;

/**
 *
 */
public interface IScenarios extends INamedSimioCollection<IScenario>, INamedMutableSimioCollection<IScenario> {
    IScenario CreateScenario(String Name);
}
