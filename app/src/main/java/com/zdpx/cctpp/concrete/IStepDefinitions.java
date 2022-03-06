package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.simuApi.extensions.IStepDefinition;

/**
 *
 */
public interface IStepDefinitions {
    int Count();
    IStepDefinition get(int index);
    IStepDefinition getStepDefinitionByGuid(Guid id);
}
