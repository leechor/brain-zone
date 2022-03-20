package org.licho.brain.concrete.entity;

import org.licho.brain.concrete.IRunSpace;
import org.licho.brain.concrete.IntelligentObjectRunSpace;
import org.licho.brain.concrete.NumericDataPropertyRow;
import org.licho.brain.concrete.Properties;
import org.licho.brain.concrete.StringPropertyDefinition;

/**
 *
 */
public class EnumPropertyRow extends NumericDataPropertyRow {


    public final Enum<?> enumValue;

    public EnumPropertyRow(StringPropertyDefinition propertyDefinitionInfo, Properties properties) {
        super(propertyDefinitionInfo, properties);
        EnumPropertyDefinition enumPropertyDefinitionInfo =
                (EnumPropertyDefinition) super.getStringPropertyDefinition();
        this.enumValue = enumPropertyDefinitionInfo.enumValue;
    }

    @Override
    public Object GetObjectValue() {
        return this.enumValue;
    }

    public Enum<?> getObjectValue(IRunSpace runSpace, IntelligentObjectRunSpace intelligentObjectRunSpace) {
        return this.getObjectValue(runSpace, intelligentObjectRunSpace, -1);
    }

    public Enum getObjectValue(IRunSpace runSpace, IntelligentObjectRunSpace intelligentObjectRunSpace, int index) {
        return (Enum) super.getObjectValue(runSpace, intelligentObjectRunSpace, index);
    }
}
