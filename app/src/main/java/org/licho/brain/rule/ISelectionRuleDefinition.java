package org.licho.brain.rule;

import org.licho.brain.concrete.Guid;
import org.licho.brain.concrete.IPropertyDefinitions;
import org.licho.brain.resource.Image;
import org.licho.brain.api.IPropertyReaders;
import org.licho.brain.api.extensions.ISelectionRule;

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
