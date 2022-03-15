package org.licho.brain.concrete;

import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.DataFormat;

/**
 *
 */
@PropertyDefinitionName("RateTableProperty")
public class RateTablePropertyDefinition extends StringPropertyDefinition {
    public RateTablePropertyDefinition(String name) {
        super(name);
        this.dataFormat = DataFormat.List;
    }

    @Override
    public String GetGridObjectClassName() {
        return EngineResources.RateTableProperty_ClassName;
    }


    @Override
    public String GetGridObjectDescription() {
        return EngineResources.RateTableProperty_ClassDescription;
    }


    @Override
    public IntelligentObjectProperty CreateInstance(Properties properties) {
        return new StringPropertyRow(this, properties);
    }
}
