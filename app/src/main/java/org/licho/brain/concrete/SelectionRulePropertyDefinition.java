package org.licho.brain.concrete;

import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.DataFormat;

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
    public String getObjectClassName() {
        return EngineResources.SelectionRuleProperty_ClassName;
    }

    @Override
    public String getObjectDescription() {
        return EngineResources.SelectionRuleProperty_ClassDescription;
    }

    @Override
    IValueProvider ValueProvider() {
        return new SelectionRulePropertyValueProvider(super.NullNullString());
    }
}
