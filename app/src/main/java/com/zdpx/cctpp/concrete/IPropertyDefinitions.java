package com.zdpx.cctpp.concrete;


import com.zdpx.cctpp.simuApi.INamedSimioCollection;
import com.zdpx.cctpp.simuApi.IPropertyDefinition;

import java.util.UUID;

/**
 *
 */
public interface IPropertyDefinitions extends INamedSimioCollection<IPropertyDefinition> {
    IPropertyDefinition AddStringProperty(String name, String defaultValue);

    IPropertyDefinition AddRealProperty(String name, double defaultValue);

    IPropertyDefinition AddExpressionProperty(String name, String defaultValue);

    IPropertyDefinition AddStateProperty(String name);

    IPropertyDefinition AddElementProperty(String name, UUID elementDefinitionID);

    IPropertyDefinitions AddRepeatGroup(String name, String description);
}
