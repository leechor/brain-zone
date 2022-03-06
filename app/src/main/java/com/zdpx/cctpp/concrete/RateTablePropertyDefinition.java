package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.cont.EngineResources;
import com.zdpx.cctpp.concrete.property.IntelligentObjectProperty;
import com.zdpx.cctpp.enu.DataFormat;

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
