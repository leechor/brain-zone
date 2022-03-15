package org.licho.brain.concrete;

import org.licho.brain.concrete.property.AbsBaseStepProperty;
import org.licho.brain.concrete.property.IntelligentObjectProperty;

/**
 *
 */
public class ExitPointPropertyRow extends IntelligentObjectProperty {
	public EntryPointPropertyRow entryPointPropertyRow;

	public ExitPointPropertyRow(StringPropertyDefinition propertyDefinitionInfo, Properties properties) {
        super(propertyDefinitionInfo, properties);
    }

    public void processExit(TokenRunSpace tokenRunSpace) {
// TODO: 2022/1/11 
    }

    public void addEntryPointProperty(AbsBaseStepProperty absBaseStepProperty) {
        this.addEntryPointProperty((absBaseStepProperty == null) ? null : absBaseStepProperty.EntryPointPropertyRow);
    }

    public void addEntryPointProperty(EntryPointPropertyRow entryPointPropertyRow) {
        if (entryPointPropertyRow != null && entryPointPropertyRow.AbsBaseStepProperty instanceof EndStepProperty)
        {
            entryPointPropertyRow = null;
        }
        if (this.entryPointPropertyRow != null) {
            this.entryPointPropertyRow.ExitPointPropertyRows.remove(this);
        }
        this.entryPointPropertyRow = entryPointPropertyRow;
        if (entryPointPropertyRow != null) {
            entryPointPropertyRow.ExitPointPropertyRows.add(this);
        }
    }
}
