package org.licho.brain.concrete;

import org.licho.brain.enu.DataFormat;

/**
 *
 */
@PropertyDefinitionName("ForeignKeyProperty")
public class ForeignKeyPropertyDefinition extends StringPropertyDefinition {
    public ForeignKeyPropertyDefinition(String name) {
        super(name);
        this.dataFormat = DataFormat.List;
    }
}
