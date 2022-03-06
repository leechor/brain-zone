package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.cont.EngineResources;
import com.zdpx.cctpp.concrete.property.IntelligentObjectProperty;
import com.zdpx.cctpp.enu.DataFormat;

@PropertyDefinitionName("SelectionRuleProperty")
public class SelectionRulePropertyDefinition extends StringPropertyDefinition {

    public SelectionRulePropertyDefinition(String name) {
        super(name);
        super.NullNullString("None");
        super.DefaultString(super.NullNullString());
        this.dataFormat = DataFormat.List;
    }

    @Override
    public IntelligentObjectProperty CreateInstance(Properties properties) {
        return new SelectionRulePropertyRow(this, properties);
    }

    @Override
    public String GetGridObjectClassName() {
        return EngineResources.SelectionRuleProperty_ClassName;
    }

    @Override
    public String GetGridObjectDescription() {
        return EngineResources.SelectionRuleProperty_ClassDescription;
    }

    @Override
    IValueProvider ValueProvider() {
        return new SelectionRulePropertyValueProvider(super.NullNullString());
    }
}
