package com.zdpx.cctpp.concrete.entity;

import com.zdpx.cctpp.concrete.IRunSpace;
import com.zdpx.cctpp.concrete.IntelligentObjectRunSpace;
import com.zdpx.cctpp.concrete.NumericDataPropertyRow;
import com.zdpx.cctpp.concrete.Properties;
import com.zdpx.cctpp.concrete.StringPropertyDefinition;

/**
 *
 */
public class EnumPropertyRow extends NumericDataPropertyRow {


    public final Enum<?> enumValue;

    public EnumPropertyRow(StringPropertyDefinition propertyDefinitionInfo, Properties properties) {
        super(propertyDefinitionInfo, properties);
        EnumPropertyDefinition enumPropertyDefinitionInfo =
                (EnumPropertyDefinition) super.getStringPropertyDefinitionInfo();
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
