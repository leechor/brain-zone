package org.licho.brain.concrete;

import org.licho.brain.concrete.property.IntelligentObjectProperty;

/**
 *
 */
@PropertyDefinitionName("EntryPointProperty")
public class EntryPointPropertyDefinition extends StringPropertyDefinition {
    public EntryPointPropertyDefinition(String name) {
        super(name);
    }

    @Override
    public IntelligentObjectProperty CreateInstance(Properties properties) {
        return new EntryPointPropertyRow(this, properties);
    }

    @Override
    public boolean CanDisplayInPropertyGrid() {
        return false;
    }
}
