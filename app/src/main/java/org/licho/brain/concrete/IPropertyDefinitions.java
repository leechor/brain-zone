package org.licho.brain.concrete;


import org.licho.brain.simuApi.INamedSimioCollection;
import org.licho.brain.simuApi.IPropertyDefinition;

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
