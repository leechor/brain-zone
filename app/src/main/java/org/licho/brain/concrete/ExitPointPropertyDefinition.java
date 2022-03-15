package org.licho.brain.concrete;

import org.licho.brain.concrete.property.IntelligentObjectProperty;

/**
 *
 */
@PropertyDefinitionName("ExitPointProperty")
public class ExitPointPropertyDefinition extends StringPropertyDefinition {
    public ExitPointPropertyDefinition(String name) {
        super(name);
        super.DisplayName("");

    }

    @Override
    public IntelligentObjectProperty CreateInstance(Properties properties) {
        return new ExitPointPropertyRow(this, properties);
    }

    @Override
    public boolean CanDisplayInPropertyGrid() {
        return false;
    }
}
