package com.zdpx.cctpp.rule;

import com.zdpx.cctpp.concrete.Guid;
import com.zdpx.cctpp.concrete.IPropertyDefinitions;
import com.zdpx.cctpp.resource.Image;
import com.zdpx.cctpp.simuApi.IPropertyReaders;
import com.zdpx.cctpp.simuApi.extensions.ISelectionRule;

/**
 *
 */
public interface ISelectionRuleDefinition {
    String Name();

    String Description();

    Image Icon();

    Guid UniqueID();

    void DefineSchema(IPropertyDefinitions propertyDefinitions);

    ISelectionRule CreateRule(IPropertyReaders properties);
}
