package org.licho.brain.concrete;

import org.licho.brain.simuApi.extensions.IStepDefinition;

/**
 *
 */
public interface IStepDefinitions {
    int Count();
    IStepDefinition get(int index);
    IStepDefinition getStepDefinitionByGuid(Guid id);
}
