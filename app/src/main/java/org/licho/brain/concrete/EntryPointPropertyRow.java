package org.licho.brain.concrete;

import org.licho.brain.concrete.property.AbsBaseStepProperty;
import org.licho.brain.concrete.property.IntelligentObjectProperty;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class EntryPointPropertyRow extends IntelligentObjectProperty {
    public List<ExitPointPropertyRow> ExitPointPropertyRows;
    public org.licho.brain.concrete.property.AbsBaseStepProperty AbsBaseStepProperty;

    public EntryPointPropertyRow(EntryPointPropertyDefinition entryPointPropertyDefinition, Properties properties) {
        super(entryPointPropertyDefinition, properties);
        this.ExitPointPropertyRows = new ArrayList<ExitPointPropertyRow>(1);
        this.AbsBaseStepProperty = (AbsBaseStepProperty) properties.propertyObject;
    }
}
