package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.cont.EngineResources;
import com.zdpx.cctpp.concrete.property.IntelligentObjectProperty;
import com.zdpx.cctpp.simioEnums.ValidObjectType;

@PropertyDefinitionName("LinkProperty")
public class LinkPropertyDefinition extends ObjectInstancePropertyDefinition {


    public LinkPropertyDefinition(String name) {
        super(name, ValidObjectType.Link);
    }

    @Override
    public IntelligentObjectProperty CreateInstance(Properties properties) {
        return new LinkObjectInstancePropertyRow(this, properties);
    }

    @Override
    public String GetGridObjectClassName() {
        return EngineResources.LinkProperty_ClassName;
    }

    @Override
    public String GetGridObjectDescription() {
        return EngineResources.LinkProperty_ClassDescription;
    }


    @Override
    public AbsDefinition ElementReferenceValueType()

    {
        return LinkDefinition.LinkFacility;
    }
}
